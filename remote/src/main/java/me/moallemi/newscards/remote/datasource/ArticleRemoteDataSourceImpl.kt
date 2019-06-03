package me.moallemi.newscards.remote.datasource

import me.moallemi.newscards.data.datasource.ArticleRemoteDataSource
import me.moallemi.newscards.data.entity.ArticlesResultEntity
import me.moallemi.newscards.data.entity.Either
import me.moallemi.newscards.remote.api.NewsApiService
import me.moallemi.newscards.remote.dto.toArticlesResultEntity
import me.moallemi.newscards.remote.extension.awaitEither

class ArticleRemoteDataSourceImpl(private val newsApiService: NewsApiService) : ArticleRemoteDataSource {

    override suspend fun getTopHeadlines(): Either<ArticlesResultEntity> {
        return newsApiService.getTopHeadlines().awaitEither { articlesResultDto ->
            articlesResultDto.toArticlesResultEntity()
        }
    }
}