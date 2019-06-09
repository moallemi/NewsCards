package me.moallemi.newscards.ui.base

class Resource<out T> private constructor(val status: ResourceState, val data: T?, val failure: Throwable? = null) {

    enum class ResourceState {
        SUCCESS,
        LOADING,
        ERROR,
        SUCCESS_LOAD_MORE,
        LOADING_LOAD_MORE,
        ERROR_LOAD_MORE
    }

    companion object {

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(ResourceState.LOADING, data, null)
        }

        fun <T> loadingMore(data: T? = null): Resource<T> {
            return Resource(ResourceState.LOADING_LOAD_MORE, data, null)
        }

        fun <T> success(data: T): Resource<T> {
            return Resource(ResourceState.SUCCESS, data, null)
        }

        fun <T> successMore(data: T): Resource<T> {
            return Resource(ResourceState.SUCCESS_LOAD_MORE, data, null)
        }

        fun <T> error(throwable: Throwable? = null): Resource<T> {
            return Resource(ResourceState.ERROR, null, throwable)
        }

        fun <T> errorMore(throwable: Throwable? = null): Resource<T> {
            return Resource(ResourceState.ERROR_LOAD_MORE, null, throwable)
        }
    }
}