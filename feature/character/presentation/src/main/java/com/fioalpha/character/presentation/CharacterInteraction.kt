package com.fioalpha.character.presentation

sealed interface CharacterInteraction {
    data class LoadingData(val offset: Int): CharacterInteraction
}