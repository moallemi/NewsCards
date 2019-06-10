package me.moallemi.newscards.model

import me.moallemi.newscards.R

data class ArticleItem(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: SourceItem,
    val title: String,
    val url: String,
    val urlToImage: String?
) : RecyclerData {
    override val viewType: Int
        get() = R.layout.item_article_item
}

data class SourceItem(
    val id: String?,
    val name: String
)