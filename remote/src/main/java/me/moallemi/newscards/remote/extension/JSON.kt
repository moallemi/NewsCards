package me.moallemi.newscards.remote.extension

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object JSON {

    val gson = GsonBuilder().create()

    inline fun <reified T> parse(json: String): T = gson.fromJson(json, object : TypeToken<T>() {}.type)
}