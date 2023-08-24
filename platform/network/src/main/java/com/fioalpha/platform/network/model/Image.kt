package com.fioalpha.platform.network.model

data class Image(
    val path: String?,
    val extension: String?
) {
    fun getFullPath(): String {
        return "$path.$extension"
    }
}