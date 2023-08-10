package com.fioalpha.platform.network.model

data class ResultWrapper<T>(
    val code: Int?,
    val status: String?,
    val etag: String?,
    val data: DataWrapper<T>?
) {
    data class DataWrapper<T>(
        val offset: Int,
        val limit: Int,
        val total: Int,
        val count: Int,
        val results: List<T>
    )
}