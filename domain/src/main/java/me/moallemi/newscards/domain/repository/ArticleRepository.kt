package me.moallemi.newscards.domain.repository

import io.reactivex.Single
import me.moallemi.newscards.domain.model.ArticlesResult

interface ArticleRepository {
    fun getTopHeadlines(): Single<ArticlesResult>
}