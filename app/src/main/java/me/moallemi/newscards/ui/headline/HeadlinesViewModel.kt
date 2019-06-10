package me.moallemi.newscards.ui.headline

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.moallemi.newscards.data.entity.ArticlesResult
import me.moallemi.newscards.data.entity.fold
import me.moallemi.newscards.data.repository.ArticleRepository
import me.moallemi.newscards.model.ArticleItem
import me.moallemi.newscards.model.toArticleItem
import me.moallemi.newscards.ui.base.recycler.BaseRecyclerViewModel
import javax.inject.Inject

class HeadlinesViewModel @Inject constructor(private val repository: ArticleRepository) :
    BaseRecyclerViewModel<ArticleItem, Unit>() {

    override fun makeData(params: Unit) {
        viewModelScope.launch {
            repository.getTopHeadlines().fold(::success, ::error)
        }
    }

    private fun success(result: ArticlesResult) {
        handleSuccess(result.articles.map { article ->
            article.toArticleItem()
        })
    }
}