package me.moallemi.newscards.ui.base.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.moallemi.newscards.model.RecyclerData
import me.moallemi.newscards.ui.base.listener.OnRecyclerItemClickListener

abstract class BaseRecyclerViewHolder<T : RecyclerData>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var onItemClickListener: OnRecyclerItemClickListener<T>? = null

    abstract fun bind(item: T)
}