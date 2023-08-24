package com.fioalpha.feature.character.data

import com.fioalpha.feature.character.data.model.CharactersWrapper

interface CharacterRepository {
    suspend fun fetchCharacters(offset: Int = 0, limit: Int = 20): CharactersWrapper
}


