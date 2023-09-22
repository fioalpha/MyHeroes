package com.fioalpha.character.presentation.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fioalpha.character.presentation.CharacterInteraction
import com.fioalpha.character.presentation.CharacterViewModel
import com.fioalpha.character.presentation.CharacterViewState
import com.fioalpha.ui.components.heroes.model.CharacterView
import com.fioalpha.ui.components.heroes.ItemCharacterContainer
import com.fioalpha.ui.components.heroes.Loading
import com.fioalpha.ui.components.heroes.PageContainer

@Composable
fun CharactersScreen(
    characterViewModel: CharacterViewModel = viewModel()
) {
    val state = characterViewModel.bind().collectAsState()
    CharactersScreenState(state.value) {
        characterViewModel.handle(it)
    }
}

@Composable
fun CharactersScreenState(
    state: CharacterViewState,
    action: (CharacterInteraction) -> Unit
) {
    PageContainer(title = "Heroes") {
        when (state) {
            is CharacterViewState.Data -> {
                ItemCharacterContainer(characters(state), state.isUpdateData) {
                    action.invoke(CharacterInteraction.MoreLoadingData(it))
                }
            }
            is CharacterViewState.Error -> Log.e(
                "ERROR",
                state.message.orEmpty()
            )

            CharacterViewState.Init -> {
                action.invoke(CharacterInteraction.Init)
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
    CharactersScreen()
}

@Preview()
@Composable
fun CharactersPageDataPreview() {
    CharactersScreen()
}
