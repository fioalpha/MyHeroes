package com.fioalpha.feature.character.data

import com.fioalpha.platform.network.HeroesService
import com.fioalpha.platform.network.model.Character
import com.fioalpha.platform.network.model.ResultWrapper
import com.fioalpha.testhelper.fromObject
import com.fioalpha.testhelper.loadFile
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {

    private val service: HeroesService = mockk()
    private lateinit var repository: CharacterRepository
    private val gson: Gson = Gson()

    @Before
    fun setup() {
        repository = CharacterRepositoryImpl(service)
    }

    @Test
    fun `should return characters`() = runTest {
        coEvery {
            service.charactersHeroes()
        } returns loadFile("result_wrapper.json").fromObject()

        repository.fetchCharacters()
            .run {
                assertThat(this.count).isEqualTo(3)
            }
    }

    @Test(expected = RuntimeException::class)
    fun `should exception with code 400`() = runTest {
        coEvery {
            service.charactersHeroes()
        } returns loadFile("result_wrapper_400_code.json").fromObject()

        repository.fetchCharacters()
    }

}