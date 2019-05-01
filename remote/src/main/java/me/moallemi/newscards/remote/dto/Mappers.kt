package me.moallemi.newscards.remote.dto

import me.moallemi.newscards.data.entity.ArticleEntity
import me.moallemi.newscards.data.entity.ArticlesResultEntity
import me.moallemi.newscards.data.entity.SourceEntity

fun ArticlesResultDto.toArticlesResultEntity() = ArticlesResultEntity(
    totalResults = totalResults,
    articles = articles.map { articleDto -> articleDto.toArticleEntity() }
)

fun ArticleDto.toArticleEntity() = ArticleEntity(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.toSourceEntity(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun SourceDto.toSourceEntity() = SourceEntity(
    id = id,
    name = name
)