package me.moallemi.newscards.remote.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.parse(json: String): T = fromJson(json, object : TypeToken<T>() {}.type)