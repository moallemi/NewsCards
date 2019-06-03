package me.moallemi.newscards.data.entity

sealed class Error(open val throwable: Throwable) : Throwable(cause = throwable) {
    class NetworkConnection(override val throwable: Throwable) : Error(throwable)
    class Server(override val throwable: Throwable) : Error(throwable)
    class Api(val code: String, override val message: String, override val throwable: Throwable) : Error(throwable)
}