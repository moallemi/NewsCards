package me.moallemi.newscards.ui.base.recycler.loadmore

import android.view.View
import me.moallemi.newscards.databinding.ItemLoadMoreBinding
import me.moallemi.newscards.ui.base.listener.TryAgainClickListener
import me.moallemi.newscards.ui.base.recycler.BaseRecyclerViewHolder

class MoreViewHolder(
    private val itemBinding: ItemLoadMoreBinding,
    private val tryAgainListener: TryAgainClickListener?
) : BaseRecyclerViewHolder<MoreItem>(itemBinding) {

    override fun bindData(item: MoreItem) {
        when (item.state) {
            State.Error -> {
                item.message?.let { message -> itemBinding.errorText.text = message }
                itemBinding.errorText.visibility = View.VISIBLE
                itemBinding.loading.visibility = View.GONE

                itemBinding.refresh.visibility = View.VISIBLE
                itemBinding.refresh.setOnClickListener { tryAgainListener?.tryAgain() }
            }
            State.Loading -> {
                itemBinding.errorText.visibility = View.GONE
                itemBinding.refresh.visibility = View.GONE
                itemBinding.loading.visibility = View.VISIBLE
            }
        }
    }
}