package com.interview.payments.domain.usecase

import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.repository.PaymentCardRepository
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val repository: PaymentCardRepository ) : UseCase<List<Card>> {
    override suspend fun execute(): List<Card> = repository.getCards()
}