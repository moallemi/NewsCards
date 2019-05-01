package me.moallemi.newscards.data.repository

import io.reactivex.Single
import me.moallemi.newscards.data.datasource.ArticleRemoteDataSource
import me.moallemi.newscards.data.entity.toArticlesResult
import me.moallemi.newscards.domain.model.ArticlesResult
import me.moallemi.newscards.domain.repository.ArticleRepository

class ArticleRepositoryImpl(private val articleRemoteDataSource: ArticleRemoteDataSource) : ArticleRepository {

    override fun getTopHeadlines(): Single<ArticlesResult> {
        return articleRemoteDataSource.getTopHeadlines()
            .map { articlesResultEntity -> articlesResultEntity.toArticlesResult() }
    }
}