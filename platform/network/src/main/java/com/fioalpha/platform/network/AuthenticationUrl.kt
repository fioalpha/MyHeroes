package com.fioalpha.platform.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Date

internal class AuthenticationUrl(
    private val time: Long = Date().time,
    private val privateKey: String = "45f27290cbe76b21e99247f8414e8204bb950230",
    private val publicKey: String = "025f2cd3ea0aaa7b9c445d8a5897de59",
): Interceptor {

    private var hashAuthentication: String? = null
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request.newBuilder()
                .url(handleUrl(request))
                .build()
        )
    }

    private fun handleUrl(request: Request): HttpUrl {
        if (request.url.queryParameter("hash") != null)
            return request.url
        return request.url.newBuilder()
            .addQueryParameter("ts", time.toString())
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", getHash())
            .build()
    }

    private fun getHash(): String {
        return hashAuthentication?: handleAuthentication("$time$privateKey$publicKey").apply {
            hashAuthentication = this
        }
    }

    private fun handleAuthentication(data: String): String {
        val md5Digest = MessageDigest.getInstance("MD5")
        return BigInteger(1, md5Digest.digest(data.toByteArray()))
            .toString(16)
            .padStart(32, '0')
    }

}