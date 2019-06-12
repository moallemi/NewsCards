package me.moallemi.newscards.remote.extension

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object JSON {

    val gson: Gson = GsonBuilder().create()

    inline fun <reified T> parse(json: String): T = gson.fromJson(json, object : TypeToken<T>() {}.type)
}