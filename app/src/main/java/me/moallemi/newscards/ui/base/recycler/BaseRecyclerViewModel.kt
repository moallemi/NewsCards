package me.moallemi.newscards.ui.base.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.moallemi.newscards.model.RecyclerData
import me.moallemi.newscards.ui.base.BaseViewModel
import me.moallemi.newscards.ui.base.Resource
import me.moallemi.newscards.ui.base.Resource.ResourceState.LOADING
import me.moallemi.newscards.ui.base.Resource.ResourceState.LOADING_LOAD_MORE

abstract class BaseRecyclerViewModel<T : RecyclerData, Params> : BaseViewModel() {

    private val _items = MutableLiveData<Resource<List<T>>>()
    val items: LiveData<Resource<List<T>>> = _items

    var allItems: Resource<List<T>>? = null

    protected open var pageSize = 15
    protected open val page
        get() = allItems?.data?.size?.div(pageSize) ?: 0

    private var endOfList = false

    protected abstract fun makeData(params: Params)

    fun load(params: Params) {
        if (allItems?.data?.isNotEmpty() == true) {
            _items.value = allItems
        } else {
            _items.value = Resource.loading()
            makeData(params)
        }
    }

    fun loadMore(params: Params) {
        if (!endOfList && _items.value?.status != LOADING_LOAD_MORE) {
            _items.value = Resource.loadingMore(_items.value?.data)
            makeData(params)
        }
    }

    protected fun error(throwable: Throwable) {
        _items.value = if (_items.value?.status == LOADING) {
            Resource.error(throwable)
        } else {
            Resource.errorMore(throwable)
        }
    }

    protected fun handleSuccess(items: List<T>) {
        endOfList = items.size < pageSize

        if (_items.value?.status == LOADING_LOAD_MORE) {
            this.allItems = Resource.success(allItems?.data!!.plus(items))
            _items.value = Resource.successMore(items)
        } else {
            val resource = Resource.success(items)
            _items.value = resource
            this.allItems = resource
        }
    }
}