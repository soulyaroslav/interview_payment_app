package com.interview.payments.ext

import android.view.View

fun View.hide(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.INVISIBLE
}

fun View.show() {
    visibility = View.GONE
}