package com.interview.payments.data.dto

data class CardDto(
    val id: Int,
    val number: String,
    val expirationDay: String,
    val holderName: String
)
