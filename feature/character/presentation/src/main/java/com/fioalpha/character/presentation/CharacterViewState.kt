package com.fioalpha.character.presentation

import com.fioalpha.ui.components.heroes.CharacterView

sealed interface CharacterViewState {
    data class Error(val message: String): CharacterViewState
    object Init: CharacterViewState
    object Loading: CharacterViewState
    data class Data(val data: List<CharacterView>, val page: Int = 0, val isUpdateData: Boolean = false): CharacterViewState

}