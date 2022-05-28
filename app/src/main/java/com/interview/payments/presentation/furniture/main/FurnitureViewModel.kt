package com.interview.payments.presentation.furniture.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.domain.usecase.GetFurnitureUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FurnitureViewModel(private val getFurnitureUseCase: GetFurnitureUseCase = GetFurnitureUseCase()) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.LoadingContent)
    val state: StateFlow<State> = _state

    init {
        getFurniture()
    }

    private fun getFurniture() {
        viewModelScope.launch {
            val furniture = getFurnitureUseCase.execute()
            _state.emit(State.UpdatingContent(furniture))
        }
    }

    sealed class State {
        object LoadingContent : State()
        data class UpdatingContent(val furniture: List<Furniture>) : State()
    }
}