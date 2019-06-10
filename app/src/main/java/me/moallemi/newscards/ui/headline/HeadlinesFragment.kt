package me.moallemi.newscards.ui.headline

import androidx.fragment.app.viewModels
import me.moallemi.newscards.model.ArticleItem
import me.moallemi.newscards.ui.base.recycler.BaseRecyclerAdapter
import me.moallemi.newscards.ui.base.recycler.BaseRecyclerFragment

class HeadlinesFragment : BaseRecyclerFragment<ArticleItem, Unit, HeadlinesViewModel>() {

    override val adapter: BaseRecyclerAdapter<ArticleItem> = HeadlinesAdapter()
    override val viewModel: HeadlinesViewModel by viewModels { viewModelFactory }

    override fun getParams() {
    }
}