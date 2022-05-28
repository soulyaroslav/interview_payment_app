package com.interview.payments.domain.repository

import com.interview.payments.domain.pojo.Card

interface CardRepository {
    suspend fun getCards(): List<Card>
}