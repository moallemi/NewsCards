package me.moallemi.newscards.ui.headline

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.moallemi.newscards.data.entity.fold
import me.moallemi.newscards.data.repository.ArticleRepository
import me.moallemi.newscards.ui.base.BaseViewModel
import javax.inject.Inject

class HeadlinesViewModel @Inject constructor(private val repository: ArticleRepository) : BaseViewModel() {

    fun load() {
        viewModelScope.launch {
            repository.getTopHeadlines().fold(
                {
                    println(it)
                }, {
                    println(it)
                }
            )
        }
    }
}