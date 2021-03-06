package me.moallemi.newscards.data.repository

import me.moallemi.newscards.data.datasource.ArticleRemoteDataSource
import me.moallemi.newscards.data.entity.ArticlesResult
import me.moallemi.newscards.data.entity.Either
import me.moallemi.newscards.data.entity.map
import me.moallemi.newscards.data.entity.toArticlesResult
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val articleRemoteDataSource: ArticleRemoteDataSource) {

    suspend fun getTopHeadlines(): Either<ArticlesResult> {
        return articleRemoteDataSource.getTopHeadlines()
            .map { articlesResultEntity -> articlesResultEntity.toArticlesResult() }
    }
}