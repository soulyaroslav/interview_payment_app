package com.interview.payments.data.dto

import androidx.annotation.DrawableRes

data class FurnitureDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPrice: Int? = null,
    @DrawableRes val image: Int
)
