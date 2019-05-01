package me.moallemi.newscards.remote.dto

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: String
)