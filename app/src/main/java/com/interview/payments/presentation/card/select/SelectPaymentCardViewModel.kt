package com.interview.payments.presentation.card.select

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.usecase.GetCardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SelectPaymentCardViewModel(
    private val getCardsUseCase: GetCardsUseCase = GetCardsUseCase()
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.LoadingContent)
    val state: StateFlow<State> = _state

    init {
        getCards()
    }

    private fun getCards() {
        viewModelScope.launch {
            val furniture = getCardsUseCase.execute()
            _state.emit(State.UpdatingContent(furniture))
        }
    }

    sealed class State {
        object LoadingContent : State()
        data class UpdatingContent(val cards: List<Card>) : State()
    }
}