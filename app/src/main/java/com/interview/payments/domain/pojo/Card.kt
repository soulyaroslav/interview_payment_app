package com.interview.payments.domain.pojo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Card(
    val number: String,
    val expirationDay: String,
    val holderName: String
) : Parcelable
