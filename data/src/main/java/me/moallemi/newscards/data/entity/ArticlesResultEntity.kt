package me.moallemi.newscards.data.entity

data class ArticlesResultEntity(
    val totalResults: Int,
    val articles: List<ArticleEntity>
)

data class ArticleEntity(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: SourceEntity,
    val title: String,
    val url: String,
    val urlToImage: String?
)

data class SourceEntity(
    val id: String?,
    val name: String
)