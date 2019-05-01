package me.moallemi.newscards.remote.dto

import com.google.gson.annotations.SerializedName

data class ArticlesResultDto(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleDto>
)

data class ArticleDto(
    @SerializedName("author") val author: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("source") val source: SourceDto,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?
)

data class SourceDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)