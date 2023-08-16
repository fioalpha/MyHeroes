package com.fioalpha.character.presentation

sealed interface CharacterViewState {
    data class Error(val message: String): CharacterViewState
    object Init: CharacterViewState
    object Loading: CharacterViewState
    data class Data(val data: Any): CharacterViewState

}