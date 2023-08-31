package com.fioalpha.feature.character.data

import com.fioalpha.feature.character.data.model.Character
import com.fioalpha.feature.character.data.model.CharactersWrapper
import com.fioalpha.platform.network.HeroesService
import com.fioalpha.platform.network.HeroesServiceBuilder

class CharacterRepositoryImpl(
    private val service: HeroesService ,
): CharacterRepository {
    override suspend fun fetchCharacters(offset: Int, limit: Int): CharactersWrapper {
       val result = service.charactersHeroes(limit, offset)
        if (result.code == 200) {
            return CharactersWrapper(
                count = result.data?.count ?: 0,
                characters = result.data?.results?.map {
                    Character(
                        id = it.id ?: 0,
                        name = it.name,
                        description = it.description.orEmpty(),
                        path = it.thumbnail.getFullPath()
                    )
                } ?: emptyList()
            )
        }
        throw RuntimeException("Has error, status code: ${result.code}")
    }

}

object CharacterRepositoryFactory {
    fun create(): CharacterRepository {
        return CharacterRepositoryImpl(HeroesServiceBuilder.build(BuildConfig.URL_BASE))
    }
}