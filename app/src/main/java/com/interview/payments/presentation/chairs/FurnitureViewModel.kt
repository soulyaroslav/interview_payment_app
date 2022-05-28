package com.interview.payments.presentation.chairs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.payments.PaymentApplication
import com.interview.payments.domain.usecase.GetFurnitureUseCase
import com.interview.payments.presentation.chairs.adapter.FurnitureItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FurnitureViewModel(private val getFurnitureUseCase: GetFurnitureUseCase = GetFurnitureUseCase()) : ViewModel() {

    private val _state = MutableStateFlow<FurnitureState>(FurnitureState.LoadingContent)
    val state: StateFlow<FurnitureState> = _state

    init {
        getFurniture()
    }

    private fun getFurniture() {
        viewModelScope.launch {
            val furniture = getFurnitureUseCase.execute()
            _state.emit(FurnitureState.UpdatingContent(furniture))
        }
    }
}

sealed class FurnitureState {
    object LoadingContent : FurnitureState()
    data class UpdatingContent(val furniture: List<FurnitureItem>) : FurnitureState()
}