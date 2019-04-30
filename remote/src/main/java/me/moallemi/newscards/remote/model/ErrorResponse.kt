package me.moallemi.newscards.remote.model

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: String
)