package com.fioalpha.platform.network

import com.fioalpha.platform.network.model.Character
import com.fioalpha.platform.network.model.ResultWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesService {
    @GET("characters")
    suspend fun charactersHeroes(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ResultWrapper<Character>
}