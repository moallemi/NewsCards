package me.moallemi.newscards.model

import java.io.Serializable

interface RecyclerData : Serializable {
    val viewType: Int
}