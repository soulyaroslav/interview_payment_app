package com.interview.payments.data.repository

import com.interview.payments.data.MockApiService
import com.interview.payments.domain.CoroutineDispatcherProvider
import com.interview.payments.domain.pojo.Card
import com.interview.payments.domain.repository.PaymentCardRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentCardRepositoryImpl @Inject constructor(
    private val provider: CoroutineDispatcherProvider,
    private val service: MockApiService
) : PaymentCardRepository {

    override suspend fun getCards(): List<Card> =
        withContext(provider.io) { service.getMockCard() }

    override suspend fun makePayment(card: Card): Boolean =
        withContext(provider.io) {
            service.makePayment(card)
        }
}