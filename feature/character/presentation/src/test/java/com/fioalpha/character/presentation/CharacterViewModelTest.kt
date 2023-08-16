package com.fioalpha.character.presentation

import com.fioalpha.feature.character.data.CharacterRepository
import com.fioalpha.feature.character.data.model.CharactersWrapper
import com.fioalpha.testhelper.MainDispatcherRule
import com.google.common.truth.Truth
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val repository: CharacterRepository = object: CharacterRepository {
        override suspend fun fetchCharacters(offset: Int, limit: Int): CharactersWrapper {
            delay(10)
            if (offset == 10) throw RuntimeException()
            return CharactersWrapper(characters = emptyList(), 0)
        }
    }
    private lateinit var viewModel: CharacterViewModel

    @Before
    fun setup() {
        viewModel = CharacterViewModel(repository)
    }

    @Test
    fun `should data`() = runTest {
        Truth.assertThat(viewModel.bind().value).isEqualTo(CharacterViewState.Init)
        viewModel.handle(CharacterInteraction.LoadingData(0))
        Truth.assertThat(viewModel.bind().value).isEqualTo(CharacterViewState.Loading)
        delay(10)
        Truth.assertThat(viewModel.bind().value)
            .isEqualTo(CharacterViewState.Data(
                CharactersWrapper(characters = emptyList(), 0)
            ))
    }

    @Test
    fun `should error`() = runTest {
        Truth.assertThat(viewModel.bind().value).isEqualTo(CharacterViewState.Init)
        viewModel.handle(CharacterInteraction.LoadingData(10))
        Truth.assertThat(viewModel.bind().value).isEqualTo(CharacterViewState.Loading)
        delay(10)
        Truth.assertThat(viewModel.bind().value)
            .isEqualTo(
                CharacterViewState.Error("")
            )
    }
}