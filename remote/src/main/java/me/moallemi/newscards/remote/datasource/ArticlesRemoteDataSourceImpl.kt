package me.moallemi.newscards.remote.datasource

import io.reactivex.Single
import me.moallemi.newscards.data.datasource.ArticlesRemoteDataSource
import me.moallemi.newscards.data.entity.ArticlesResultEntity
import me.moallemi.newscards.remote.api.NewsApiService
import me.moallemi.newscards.remote.dto.toArticlesResultEntity
import me.moallemi.newscards.remote.extension.toRxSingle

class ArticlesRemoteDataSourceImpl(private val newsApiService: NewsApiService) : ArticlesRemoteDataSource {

    override fun getTopHeadlines(): Single<ArticlesResultEntity> {
        return newsApiService.getTopHeadlines()
            .toRxSingle()
            .map { articlesResultDto -> articlesResultDto.toArticlesResultEntity() }
    }
}