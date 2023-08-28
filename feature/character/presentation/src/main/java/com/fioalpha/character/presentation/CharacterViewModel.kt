package com.fioalpha.character.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fioalpha.feature.character.data.CharacterRepository
import com.fioalpha.ui.components.heroes.CharacterView
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharacterRepository
): ViewModel(){
    private val interactions: Channel<CharacterInteraction> = Channel(UNLIMITED)
    private val states: MutableStateFlow<CharacterViewState> = MutableStateFlow(CharacterViewState.Init)

    init {
        viewModelScope.launch {
            interactions.consumeAsFlow().collect(::handleState)
        }
    }

    fun bind() = states.asStateFlow()

    fun handle(newInteraction: CharacterInteraction){
        viewModelScope.launch {
            interactions.send(newInteraction)
        }
    }

    private suspend fun handleState(interaction: CharacterInteraction) {
        return when(interaction) {
            is CharacterInteraction.LoadingData -> {
                states.value = CharacterViewState.Loading
                try {
                    states.value = CharacterViewState.Data(
                        data = repository.fetchCharacters(interaction.offset).characters.map {
                            CharacterView(it.path, it.name)
                        }
                    )
                } catch (ex: Exception) {
                    states.value = CharacterViewState.Error(
                        message = ex.message.orEmpty()
                    )
                }
            }
        }
    }

}
