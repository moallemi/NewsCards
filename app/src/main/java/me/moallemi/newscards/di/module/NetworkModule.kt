package me.moallemi.newscards.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import me.moallemi.newscards.BuildConfig
import me.moallemi.newscards.remote.api.NewsApiService
import me.moallemi.newscards.remote.extension.JSON
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .header("X-Api-Key", BuildConfig.API_KEY)
            .build()
        chain.proceed(request)
    }

    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BODY
            }
        }

    @Provides
    fun provideOkHttpClient(urlInterceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(urlInterceptor)
            .build()
    }

    @Provides
    fun provideGson(): Gson = JSON.gson

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NewsApiService.ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideNewsAPIService(retrofit: Retrofit): NewsApiService = retrofit.create()
}