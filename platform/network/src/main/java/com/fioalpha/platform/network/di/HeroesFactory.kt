package com.fioalpha.platform.network.di

import com.fioalpha.platform.network.HeroesService
import com.fioalpha.platform.network.HeroesServiceBuilder


interface HeroesFactory {

    companion object {
        private const val BASE_URL = "http://gateway.marvel.com/v1/public"
    }
    fun create(baseUrl: String = BASE_URL): HeroesService

    object Impl: HeroesFactory {
        private var cacheHeroesService: HeroesService? = null
        override fun create(baseUrl: String): HeroesService {
          return cacheHeroesService?: HeroesServiceBuilder.build(baseUrl)
              .apply {
                  cacheHeroesService = this
              }
        }
    }
}
