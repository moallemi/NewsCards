package me.moallemi.newscards.ui.headline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.moallemi.newscards.R
import me.moallemi.newscards.model.ArticleItem
import me.moallemi.newscards.ui.base.recycler.BaseRecyclerAdapter
import me.moallemi.newscards.ui.base.recycler.BaseRecyclerViewHolder

class HeadlinesAdapter : BaseRecyclerAdapter<ArticleItem>() {

    override fun makeViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<ArticleItem>? {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return HeadlinesViewHolder(view)
    }

    class HeadlinesViewHolder(itemView: View) : BaseRecyclerViewHolder<ArticleItem>(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)

        override fun bind(item: ArticleItem) {
            title.text = item.title
        }
    }
}