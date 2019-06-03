package me.moallemi.newscards.data.datasource

import me.moallemi.newscards.data.entity.ArticlesResultEntity
import me.moallemi.newscards.data.entity.Either

interface ArticleRemoteDataSource {
    suspend fun getTopHeadlines(): Either<ArticlesResultEntity>
}