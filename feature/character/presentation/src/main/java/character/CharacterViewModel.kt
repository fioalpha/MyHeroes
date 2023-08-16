package character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fioalpha.feature.character.data.CharacterRepository
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
                        data = repository.fetchCharacters(interaction.offset)
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
sealed interface CharacterInteraction {
    data class LoadingData(val offset: Int): CharacterInteraction
}

sealed interface CharacterViewState {
    data class Error(val message: String): CharacterViewState
    object Init: CharacterViewState
    object Loading: CharacterViewState
    data class Data(val data: Any): CharacterViewState

}