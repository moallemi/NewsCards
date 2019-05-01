package me.moallemi.newscards.data.entity

import me.moallemi.newscards.domain.model.Article
import me.moallemi.newscards.domain.model.ArticlesResult
import me.moallemi.newscards.domain.model.Source

fun ArticlesResultEntity.toArticlesResult() = ArticlesResult(
    totalResults = totalResults,
    articles = articles.map { articleDto -> articleDto.toArticle() }
)

fun ArticleEntity.toArticle() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.toSource(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun SourceEntity.toSource() = Source(
    id = id,
    name = name
)