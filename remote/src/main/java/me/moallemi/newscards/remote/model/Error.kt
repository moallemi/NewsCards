package me.moallemi.newscards.remote.model

sealed class Error(override val message: String) : Throwable(message) {
    data class NetworkConnection(override val message: String, val throwable: Throwable) : Error(message)
    data class Server(override val message: String, val throwable: Throwable) : Error(message)
    data class Api(override val message: String, val code: String) : Error(message)
}