package com.interview.payments.domain.pojo

import androidx.annotation.DrawableRes

data class Furniture(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPrice: Int? = null,
    @DrawableRes val image: Int
)
