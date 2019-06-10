package me.moallemi.newscards.model

import me.moallemi.newscards.data.entity.Article
import me.moallemi.newscards.data.entity.Source

fun Article.toArticleItem() = ArticleItem(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.toSourceItem(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun Source.toSourceItem() = SourceItem(
    id = id,
    name = name
)