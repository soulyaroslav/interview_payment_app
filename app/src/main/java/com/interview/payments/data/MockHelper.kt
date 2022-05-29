package com.interview.payments.data

import com.interview.payments.R

object MockHelper {
    const val DELAY = 3000L

    fun randImage(): Int {
        val rand = (0..2).random()
        return furnitureImages[rand]
    }

    private val furnitureImages = listOf(
        R.drawable.chair_0,
        R.drawable.chair_1,
        R.drawable.chair_2
    )

    enum class CardErrorType(val number: String) {
        BALANCE_INSUFFICIENT("1111 1111 1111 1111"),
        CARD_DECLINE("1111 1111 1111 2222"),
        EXPIRED_CARD("1111 1111 1111 3333"),
    }
}