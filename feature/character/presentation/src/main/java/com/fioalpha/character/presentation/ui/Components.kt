package com.fioalpha.character.presentation.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fioalpha.character.presentation.CharacterInteraction
import com.fioalpha.character.presentation.CharacterViewModel
import com.fioalpha.character.presentation.CharacterViewState
import com.fioalpha.ui.components.heroes.CharacterView
import com.fioalpha.ui.components.heroes.ItemsCharacters
import com.fioalpha.ui.components.heroes.Loading
import com.fioalpha.ui.components.heroes.PageContainer
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CharactersPage(
    characterViewModel: CharacterViewModel = viewModel()
) {
    val state = characterViewModel.bind().collectAsState()
    CharactersPageState(state.value) {
        characterViewModel.handle(it)
    }
}

@Composable
fun CharactersPageState(
    state: CharacterViewState,
    action: (CharacterInteraction) -> Unit
) {
    PageContainer(title = "Heroes") {
        when (state) {
            is CharacterViewState.Data -> ItemsCharacters(characters(state))
            is CharacterViewState.Error -> Log.e(
                "ERROR",
                state.message.orEmpty()
            )

            CharacterViewState.Init -> {
                action.invoke(CharacterInteraction.LoadingData(0))
            }

            CharacterViewState.Loading -> Loading()
        }
    }
}

fun characters(data: CharacterViewState): List<CharacterView>{
    return (data as? CharacterViewState.Data)?.data?.map {
        CharacterView(it.pathImage, it.name)
    }?: emptyList()
}
@Preview()
@Composable
fun CharactersPagePreview() {
    CharactersPage()
}

@Preview()
@Composable
fun CharactersPageDataPreview() {
    CharactersPage()
}
