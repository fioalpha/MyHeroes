package com.fioalpha.character.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fioalpha.feature.character.data.CharacterRepository
import com.fioalpha.ui.components.heroes.CharacterView
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharacterRepository
): ViewModel(){
    private val interactions: Channel<CharacterInteraction> = Channel(UNLIMITED)
    private val states: MutableStateFlow<CharacterViewState> = MutableStateFlow(CharacterViewState.Init)
    private var data = CharacterViewState.Data(emptyList())
    init {
        viewModelScope.launch {
            interactions.consumeAsFlow().distinctUntilChanged().collect(::handleState)
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
            CharacterInteraction.Init -> {
                states.value = CharacterViewState.Loading
                loadData(0)
            }
            is CharacterInteraction.MoreLoadingData -> {
                states.value = data.copy(isUpdateData = true, page = interaction.offset)
                loadData(interaction.offset)
            }
        }
    }

    private suspend fun loadData(offset: Int) {
        try {
            val newData = repository.fetchCharacters(offset * 10).characters.map {
                CharacterView(it.path, it.name)
            }
            data = data.copy(
                data = data.data.plus(newData),
                page = data.page.inc() ,
                isUpdateData = false
            )
            states.value = data
        } catch (ex: Exception) {
            states.value = CharacterViewState.Error(
                message = ex.message.orEmpty()
            )
        }
    }

}
