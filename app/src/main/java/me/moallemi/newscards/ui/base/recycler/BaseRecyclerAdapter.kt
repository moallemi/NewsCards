package me.moallemi.newscards.ui.base.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.moallemi.newscards.R
import me.moallemi.newscards.model.RecyclerData
import me.moallemi.newscards.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.newscards.ui.base.listener.TryAgainClickListener
import me.moallemi.newscards.ui.base.recycler.loadmore.MoreItem
import me.moallemi.newscards.ui.base.recycler.loadmore.MoreViewHolder

abstract class BaseRecyclerAdapter<T : RecyclerData> : RecyclerView.Adapter<BaseRecyclerViewHolder<T>>() {

    var itemClickListener: OnRecyclerItemClickListener<T>? = null
    var tryAgainListener: TryAgainClickListener? = null

    var items: MutableList<T> = mutableListOf()

    protected abstract fun makeViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<T>?

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<T> {
        return when (viewType) {
            MoreItem.VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_load_more, parent, false)
                MoreViewHolder(view, tryAgainListener) as BaseRecyclerViewHolder<T>
            }
            else -> {
                makeViewHolder(parent, viewType)?.apply {
                    onItemClickListener = itemClickListener
                } ?: run {
                    throw RuntimeException("ViewHolder must not be null")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    private fun removeLoadingItem() {
        if (items.size != 0 && items.last() is MoreItem) {
            removeLastItem()
        }
    }

    fun addItem(item: T) {
        removeLoadingItem()
        items.add(item)
        notifyItemInserted(items.lastIndex)
    }

    fun addItem(items: List<T>) {
        removeLoadingItem()
        val lastPosition = this.items.size
        this.items.addAll(items)
        notifyItemRangeInserted(lastPosition, items.size)
    }

    private fun removeLastItem() {
        val lastIndex = items.lastIndex
        items.removeAt(lastIndex)
        notifyItemRemoved(lastIndex)
    }
}