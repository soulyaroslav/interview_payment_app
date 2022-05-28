package com.interview.payments.presentation.chairs.adapter

import android.text.SpannedString
import androidx.annotation.DrawableRes

data class FurnitureItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: SpannedString,
    @DrawableRes val image: Int
)
