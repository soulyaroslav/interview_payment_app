package com.interview.payments.ext

import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.*
import com.interview.payments.R
import com.interview.payments.domain.pojo.Furniture


fun TextView.prepareFurnitureSpannable(furniture: Furniture) {
    text = furniture.discountPrice?.let { discount ->
        val backgroundColor = ContextCompat.getColor(context, R.color.yellow)
        val discountTextColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
        val lastPriceTextColor = ContextCompat.getColor(context, android.R.color.black)
        buildSpannedString {
            backgroundColor(backgroundColor) {
                inSpans(ForegroundColorSpan(discountTextColor)) {
                    append(" $")
                    append(discount.toString())
                    append(" ")
                }
            }
            append(" ")
            strikeThrough {
                scale(.6f) {
                    inSpans(ForegroundColorSpan(lastPriceTextColor)) {
                        append("$")
                        append(furniture.price.toString())
                    }
                }
            }
        }
    } ?: SpannedString("${furniture.price}\$")
}