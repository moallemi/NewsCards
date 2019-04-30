package me.moallemi.newscards.remote

import io.reactivex.Single
import me.moallemi.newscards.remote.api.NewsApiService
import me.moallemi.newscards.remote.extension.toRxSingle
import me.moallemi.newscards.remote.model.ArticlesResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ArticlesRemoteDataSource(private val newsApiService: NewsApiService) {

    fun getTopHeadlines(): Single<ArticlesResponse> {
        return newsApiService.getTopHeadlines().toRxSingle()
    }
}