package com.interview.payments.presentation.card.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.pojo.ValidationResult
import com.interview.payments.domain.usecase.IsPaymentCardValidUseCase
import com.interview.payments.domain.usecase.MakePaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val isPaymentCardValidUseCase: IsPaymentCardValidUseCase,
    private val makePaymentUseCase: MakePaymentUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Init)
    val state: StateFlow<State> = _state

    fun validatePaymentCard(number: String, expirationDay: String, secureCode: String, holderName: String) {
        viewModelScope.launch {
            _state.emit(State.LoadingContent)
            delay(1000)
            val params = IsPaymentCardValidUseCase.Params.toParams(number, expirationDay, secureCode, holderName)
            val result = isPaymentCardValidUseCase.execute(params)
            _state.emit(State.ValidationPayment(result))
        }
    }

    fun makePayment(number: String, expirationDay: String, secureCode: String, holderName: String) {
        viewModelScope.launch {
            val params = MakePaymentUseCase.Params.toParams(number, expirationDay, secureCode, holderName)
            try {
                makePaymentUseCase.execute(params)
                _state.emit(State.PaymentSuccess)
            } catch (e: Exception) {
                _state.emit(State.PaymentError(e.message ?: ""))
            }
        }
    }

    sealed class State {
        object Init : State()
        object LoadingContent : State()
        object PaymentSuccess : State()
        data class PaymentError(val error: String) : State()
        data class ValidationPayment(val validationResult: ValidationResult) : State()
    }
}