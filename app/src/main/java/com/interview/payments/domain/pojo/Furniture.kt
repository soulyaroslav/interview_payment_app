package com.interview.payments.domain.pojo

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

/**
 * Here I have applied Parcelize to the Furniture object that situated on the domain layer
 * for simplicity, I think in real project better to have separate object for presentation layer
 * and apply Parcelize to it
 */
@Parcelize
data class Furniture(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPrice: Int? = null,
    @DrawableRes val image: Int
): Parcelable
