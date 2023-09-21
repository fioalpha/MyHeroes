package com.fioalpha.testhelper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun Any.loadFile(path: String) = this.javaClass
    .classLoader
    .getResourceAsStream(path)
    ?.bufferedReader()
    .use { it?.readText() }

inline fun <reified T> String.fromObject() = Gson().fromJson<T>(this, object: TypeToken<T>() {}.type)