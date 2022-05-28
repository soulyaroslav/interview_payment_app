package com.interview.payments.domain.usecase

import com.interview.payments.data.repository.CardRepositoryImpl
import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.repository.CardRepository

class GetCardsUseCase(private val repository: CardRepository = CardRepositoryImpl()) : UseCase<List<Card>> {
    override suspend fun execute(): List<Card> = repository.getCards()
}