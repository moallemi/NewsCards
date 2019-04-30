package me.moallemi.newscards.remote.api

import me.moallemi.newscards.remote.model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("language") language: String = "en"): Call<ArticlesResponse>

    companion object {
        const val ENDPOINT = "https://newsapi.org"
    }
}