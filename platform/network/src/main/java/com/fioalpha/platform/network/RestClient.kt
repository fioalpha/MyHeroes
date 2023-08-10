package com.fioalpha.platform.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

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
            .callTimeout(Duration.ofSeconds(20))
            .connectTimeout(Duration.ofSeconds(20))
            .readTimeout(Duration.ofSeconds(20))
            .writeTimeout(Duration.ofSeconds(20))
            .retryOnConnectionFailure(false)
            .build()
    }

    private fun createInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}
