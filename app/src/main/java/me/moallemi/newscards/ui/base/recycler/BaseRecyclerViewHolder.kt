package me.moallemi.newscards.ui.base.recycler

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import me.moallemi.newscards.BR
import me.moallemi.newscards.model.RecyclerData
import me.moallemi.newscards.ui.base.listener.OnRecyclerItemClickListener

open class BaseRecyclerViewHolder<T : RecyclerData>(protected val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var onItemClickListener: OnRecyclerItemClickListener<T>? = null

    open fun bindData(item: T) {
    }

    fun bind(item: T) {
        bindData(item)
        binding.setVariable(BR.itemData, item)
        onItemClickListener?.let {
            binding.setVariable(BR.itemClickListener, onItemClickListener)
        }
        binding.executePendingBindings()
    }
}