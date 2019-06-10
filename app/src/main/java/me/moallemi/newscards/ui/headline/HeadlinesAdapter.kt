package me.moallemi.newscards.ui.headline

import android.view.LayoutInflater
import android.view.ViewGroup
import me.moallemi.newscards.databinding.ItemArticleItemBinding
import me.moallemi.newscards.model.ArticleItem
import me.moallemi.newscards.ui.base.recycler.BaseRecyclerAdapter
import me.moallemi.newscards.ui.base.recycler.BaseRecyclerViewHolder

class HeadlinesAdapter : BaseRecyclerAdapter<ArticleItem>() {

    override fun makeViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<ArticleItem>? {
        return BaseRecyclerViewHolder(
            ItemArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}