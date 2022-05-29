package com.interview.payments.domain.repository

import com.interview.payments.domain.pojo.Card

interface PaymentCardRepository {
    suspend fun getCards(): List<Card>
    suspend fun makePayment(card: Card): Boolean
}