package com.interview.payments.domain.usecase

import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.repository.PaymentCardRepository
import javax.inject.Inject

class MakePaymentUseCase @Inject constructor(
    private val repository: PaymentCardRepository
) : UseCaseParams<Boolean, MakePaymentUseCase.Params> {
    class Params private constructor(val number: String, val expirationDay: String, val secureCode: String, val holderName: String) {
        companion object {
            fun toParams(number: String, expirationDay: String, secureCode: String, holderName: String) =
                Params(number, expirationDay, secureCode, holderName)
        }
    }

    override suspend fun execute(params: Params): Boolean = with(params) {
        val card = Card(number, expirationDay, secureCode, holderName)
        repository.makePayment(card)
    }
}