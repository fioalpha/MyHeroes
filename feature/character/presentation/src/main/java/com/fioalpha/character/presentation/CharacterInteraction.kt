package com.fioalpha.character.presentation

sealed interface CharacterInteraction {
    object Init: CharacterInteraction
    data class MoreLoadingData(val offset: Int): CharacterInteraction
}