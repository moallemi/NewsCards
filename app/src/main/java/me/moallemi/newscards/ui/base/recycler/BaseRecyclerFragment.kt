package me.moallemi.newscards.ui.base.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.moallemi.newscards.R
import me.moallemi.newscards.extension.observe
import me.moallemi.newscards.model.RecyclerData
import me.moallemi.newscards.ui.base.BaseFragment
import me.moallemi.newscards.ui.base.Resource
import me.moallemi.newscards.ui.base.Resource.ResourceState.ERROR
import me.moallemi.newscards.ui.base.Resource.ResourceState.ERROR_LOAD_MORE
import me.moallemi.newscards.ui.base.Resource.ResourceState.LOADING
import me.moallemi.newscards.ui.base.Resource.ResourceState.LOADING_LOAD_MORE
import me.moallemi.newscards.ui.base.Resource.ResourceState.SUCCESS
import me.moallemi.newscards.ui.base.Resource.ResourceState.SUCCESS_LOAD_MORE
import me.moallemi.newscards.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.newscards.ui.base.listener.TryAgainClickListener
import me.moallemi.newscards.ui.base.recycler.loadmore.MoreItem
import me.moallemi.newscards.ui.base.recycler.loadmore.State
import java.io.IOException
import java.net.UnknownHostException

abstract class BaseRecyclerFragment<T : RecyclerData, Params, VM : BaseRecyclerViewModel<T, Params>> : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loading: ProgressBar
    private lateinit var emptyView: View
    private lateinit var errorView: AppCompatImageView
    protected open var layoutId: Int = R.layout.fragment_list

    private val visibleThreshold: Int = 2
    private var isLoadingMore = false
    protected open var isEndless = true

    protected lateinit var viewModel: VM
    protected abstract fun makeViewModel(): VM
    protected abstract fun getParams(): Params

    protected abstract val adapter: BaseRecyclerAdapter<T>
    protected var layoutManager: RecyclerView.LayoutManager? = null

    protected var recyclerItemClickListener: OnRecyclerItemClickListener<T>? = null
    private val loadMoreTryAgainListener = object : TryAgainClickListener {
        override fun tryAgain() {
            viewModel.loadMore(getParams())
        }
    }

    private val tryAgainListener = object : TryAgainClickListener {
        override fun tryAgain() {
            viewModel.load(getParams())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = makeViewModel().apply {
            observe(items, ::handleResourceState)
        }

        viewModel.load(getParams())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView) ?: throw RuntimeException("RecyclerView must not be null")
        loading = view.findViewById(R.id.loading) ?: throw RuntimeException("Loading must not be null")
        emptyView = view.findViewById(R.id.emptyView) ?: throw RuntimeException("emptyView must not be null")
        errorView = view.findViewById(R.id.errorView) ?: throw RuntimeException("errorView must not be null")

        layoutManager = LinearLayoutManager(requireContext())
        with(adapter) {
            itemClickListener = recyclerItemClickListener
            tryAgainListener = loadMoreTryAgainListener
        }

        with(recyclerView) {
            adapter = this@BaseRecyclerFragment.adapter
            layoutManager = this@BaseRecyclerFragment.layoutManager

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    processOnMore()
                }
            })
        }
    }

    private fun processOnMore() {
        val lastVisibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        val totalItemCount = layoutManager!!.itemCount

        if ((totalItemCount <= lastVisibleItem + visibleThreshold) && !isLoadingMore && isEndless) {
            viewModel.loadMore(getParams())
        }
    }

    private fun handleResourceState(resource: Resource<List<T>>?) {
        resource?.let {
            when (it.status) {
                SUCCESS -> handleData(it.data)
                SUCCESS_LOAD_MORE -> handleMoreData(it.data)
                ERROR -> it.failure?.let { failure -> handleError(failure) }
                ERROR_LOAD_MORE -> it.failure?.let { failure -> handleLoadMoreError(failure) }
                LOADING -> handleLoading()
                LOADING_LOAD_MORE -> showLoadingMore()
            }
        }
    }

    private fun handleData(data: List<T>?) {
        hideLoading()
        hideErrorView()
        if (data == null || data.isEmpty()) {
            hideRecyclerView()
            showEmptyView()
        } else {
            showRecyclerView()
            hideEmptyView()
            showData(data)
        }
    }

    private fun handleMoreData(items: List<T>?) {
        isLoadingMore = false
        showMoreData(items)
    }

    private fun showMoreData(items: List<T>?) {
        items?.let { adapter.addItem(items) }
    }

    private fun handleError(failure: Throwable) {
        hideLoading()
        hideRecyclerView()
        hideEmptyView()
        showErrorView(failure)
    }

    private fun handleLoadMoreError(failure: Throwable?) {
        isLoadingMore = false
        showLoadMoreErrorView(failure)
    }

    private fun handleLoading() {
        hideErrorView()
        hideEmptyView()
        hideRecyclerView()
        showLoading()
    }

    private fun handleLoadingMore() {
        showLoadingMore()
    }

    protected fun showData(items: List<T>) {
        adapter.items = ArrayList(items)
        adapter.notifyDataSetChanged()
    }

    protected fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    protected fun hideEmptyView() {
        emptyView.visibility = View.GONE
    }

    protected fun showErrorView(failure: Throwable?) {
        if (failure is UnknownHostException || failure is IOException) {
            errorView.setImageResource(R.drawable.ic_cloud_off_black_24dp)
        } else {
            errorView.setImageResource(R.drawable.ic_error_outline_black_24dp)
        }
        errorView.visibility = View.VISIBLE
        errorView.visibility = View.VISIBLE
        // TODO show snackBar
    }

    protected fun hideErrorView() {
        errorView.visibility = View.GONE
    }

    private fun showLoadMoreErrorView(failure: Throwable?) {
        val message = if (failure is UnknownHostException || failure is IOException) {
            getString(R.string.no_internet)
        } else {
            failure?.message
        }
        addLoadMoreData(MoreItem(State.Error, message))
    }

    protected fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    protected fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun showLoadingMore() {
        isLoadingMore = true
        addLoadMoreData(MoreItem(State.Loading))
    }

    protected fun showRecyclerView() {
        recyclerView.visibility = View.VISIBLE
    }

    protected fun hideRecyclerView() {
        recyclerView.visibility = View.GONE
    }

    @Suppress("UNCHECKED_CAST")
    fun addLoadMoreData(item: MoreItem) {
        recyclerView.post {
            adapter.addItem(item as T)
        }
    }
}