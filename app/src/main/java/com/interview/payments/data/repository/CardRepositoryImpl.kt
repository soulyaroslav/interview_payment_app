package com.interview.payments.data.repository

import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.repository.CardRepository

class CardRepositoryImpl constructor(
    private val service: MockApiService = MockApiService()
) : CardRepository {

    override suspend fun getCards(): List<Card> = service.getMockCard()
}