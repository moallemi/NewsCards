package me.moallemi.newscards.remote.datasource

import io.reactivex.Single
import me.moallemi.newscards.data.datasource.ArticleRemoteDataSource
import me.moallemi.newscards.data.entity.ArticlesResultEntity
import me.moallemi.newscards.remote.api.NewsApiService
import me.moallemi.newscards.remote.dto.toArticlesResultEntity
import me.moallemi.newscards.remote.extension.toRxSingle

class ArticleRemoteDataSourceImpl(private val newsApiService: NewsApiService) : ArticleRemoteDataSource {

    override fun getTopHeadlines(): Single<ArticlesResultEntity> {
        return newsApiService.getTopHeadlines()
            .toRxSingle()
            .map { articlesResultDto -> articlesResultDto.toArticlesResultEntity() }
    }
}