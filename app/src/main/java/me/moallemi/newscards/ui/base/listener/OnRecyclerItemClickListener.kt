package me.moallemi.newscards.ui.base.listener

import me.moallemi.newscards.model.RecyclerData

interface OnRecyclerItemClickListener<T : RecyclerData> {

    fun onItemClick(item: T)
}