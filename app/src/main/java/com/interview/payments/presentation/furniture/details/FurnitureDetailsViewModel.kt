package com.interview.payments.presentation.furniture.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.payments.domain.usecase.GetFurniturePreviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FurnitureDetailsViewModel @Inject constructor(
    private val getFurniturePreviewsUseCase: GetFurniturePreviewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.LoadingContent)
    val state: StateFlow<State> = _state

    private val eventChannel = Channel<Event>()

    init {
        viewModelScope.launch {
            eventChannel.consumeEach { handleEvent(it) }
        }
    }

    fun triggerEvent(event: Event) = viewModelScope.launch {
        eventChannel.send(event)
    }

    private fun handleEvent(event: Event) {
        when (event) {
            Event.GetFurniturePreviews -> getFurniturePreviews()
        }
    }

    private fun getFurniturePreviews() {
        viewModelScope.launch {
            val furniture = getFurniturePreviewsUseCase.execute()
            _state.emit(State.UpdatingContent(furniture))
        }
    }

    sealed class State {
        object LoadingContent : State()
        data class UpdatingContent(val previews: List<Int>) : State()
    }

    sealed class Event {
        object GetFurniturePreviews : Event()
    }
}