package me.moallemi.newscards.data.entity

/**
 * Represents a value of one of two possible types, success or failure.
 *
 * It's similar to [Result] or Arrow's [Either](https://github.com/arrow-kt/arrow/blob/master/modules/core/arrow-core/src/main/kotlin/arrow/core/Either.kt), but with restricted usage.
 *
 * As we want to use [Either] only for data exchange between data and presentation layers, It forces to use [Error] as the [Failure] type.
 *
 * @param V the success value. It marks with `out` modifier to assign a class of subtype to class of super-type, so [Either] can be downcasted to [Success] and [Failure] on `when` statement.
 */
sealed class Either<out V> {

    class Success<V>(val value: V) : Either<V>()

    class Failure(val error: Error) : Either<Nothing>()
}

inline fun <V, V2> Either<V>.map(transform: (V) -> V2): Either<V2> = when (this) {
    // TODO use contract
    is Either.Failure -> this
    is Either.Success -> Either.Success(transform(value))
}

/**
 * Applies `ifSuccess` if this is a [Either.Success] or `ifFailure` if this is a [Either.Failure].
 *
 * @param ifSuccess the function to apply if this is a [Either.Success]
 * @param ifFailure the function to apply if this is a [Either.Failure]
 * @return the results of applying the function
 */
inline fun <R, T> Either<T>.fold(
    ifSuccess: (value: T) -> R,
    ifFailure: (failure: Error) -> R
): R {
//    contract {
//        callsInPlace(ifSuccess, InvocationKind.AT_MOST_ONCE)
//        callsInPlace(ifFailure, InvocationKind.AT_MOST_ONCE)
//    }
    // TODO use contract
    return when (this) {
        is Either.Success<T> -> ifSuccess(value)
        is Either.Failure -> ifFailure(error)
    }
}