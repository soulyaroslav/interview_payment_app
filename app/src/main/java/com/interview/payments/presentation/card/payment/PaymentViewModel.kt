package com.interview.payments.presentation.card.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.pojo.ValidationResult
import com.interview.payments.domain.usecase.IsPaymentCardValidUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val isPaymentCardValidUseCase: IsPaymentCardValidUseCase = IsPaymentCardValidUseCase()
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.LoadingContent)
    val state: StateFlow<State> = _state

    fun validatePaymentCard(number: String, expirationDay: String, secureCode: String, holderName: String) {
        viewModelScope.launch {
            val params = IsPaymentCardValidUseCase.Params.toParams(number, expirationDay, secureCode, holderName)
            val result = isPaymentCardValidUseCase.execute(params)
            _state.emit(State.ValidationPayment(result))
        }
    }

    sealed class State {
        object LoadingContent : State()
        data class ValidationPayment(val validationResult: ValidationResult) : State()
    }
}