package com.fioalpha.platform.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

object HeroesServiceBuilder {
    fun build(
        baseUrl: String,
    ): HeroesService {
        return with(Retrofit.Builder()) {
            baseUrl(baseUrl)
            client(createOkhttpClient())
            addConverterFactory(GsonConverterFactory.create(Gson()))
            build()
        }.create(HeroesService::class.java)
    }

    private fun createOkhttpClient(
        interceptor: Interceptor = createInterceptor(),
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(AuthenticationUrl())
            .retryOnConnectionFailure(false)
            .build()
    }

    private fun createInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
