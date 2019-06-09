package me.moallemi.newscards.remote.extension

import kotlinx.coroutines.suspendCancellableCoroutine
import me.moallemi.newscards.data.entity.Either
import me.moallemi.newscards.data.entity.Error
import me.moallemi.newscards.data.entity.Error.Api
import me.moallemi.newscards.data.entity.Error.NetworkConnection
import me.moallemi.newscards.data.entity.Error.Server
import me.moallemi.newscards.remote.dto.ErrorResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.resume

suspend fun <T, R> Call<T>.awaitEither(map: (T) -> R): Either<R> = suspendCancellableCoroutine { continuation ->
    try {
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resume(Either.Failure(asNetworkException(t)))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    try {
                        continuation.resume(Either.Success(map(response.body()!!)))
                    } catch (throwable: Throwable) {
                        continuation.resume(Either.Failure(asNetworkException(throwable)))
                    }
                } else {
                    val t = HttpException(response)
                    continuation.resume(Either.Failure(asNetworkException(t)))
                }
            }
        })
    } catch (t: Throwable) {
        continuation.resume(Either.Failure(asNetworkException(t)))
    }
    continuation.invokeOnCancellation {
        cancel()
    }
}

suspend fun <T, R> Call<T>.syncEither(map: (T) -> R): Either<R> = suspendCancellableCoroutine { continuation ->
    try {

        val response = execute()
        if (response.code() == 200) {
            if (response.isSuccessful) {
                try {
                    continuation.resume(Either.Success(map(response.body()!!)))
                } catch (throwable: Throwable) {
                    continuation.resume(
                        Either.Failure(
                            asNetworkException(
                                throwable
                            )
                        )
                    )
                }
            } else {
                val t = HttpException(response)
                continuation.resume(Either.Failure(asNetworkException(t)))
            }
        } else {
            continuation.resume(Either.Failure(asNetworkException(HttpException(response))))
        }
    } catch (t: Throwable) {
        continuation.resume(Either.Failure(asNetworkException(t)))
    }
    continuation.invokeOnCancellation {
        cancel()
    }
}

private fun createHttpError(throwable: HttpException): Error {
    val response = throwable.response()
    return if (response?.errorBody() == null) {
        Server(IllegalStateException("response.errorBody() is null"))
    } else {
        try {
            val fromJson = JSON.parse<ErrorResponseDto>(response.errorBody()!!.string())
            Api(fromJson.code, fromJson.message, Exception())
        } catch (e: Exception) {
            Server(e)
        }
    }
}

private fun asNetworkException(throwable: Throwable): Error {
    return when (throwable) {
        is IOException -> NetworkConnection(throwable)
        is HttpException -> createHttpError(throwable)
        is Error -> throwable
        else -> Server(throwable)
    }
}
