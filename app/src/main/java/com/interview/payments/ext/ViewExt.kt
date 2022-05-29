package com.interview.payments.ext

import android.view.View

fun View.hideView(isGone: Boolean = true) {
    visibility = if (isGone) View.GONE else View.INVISIBLE
}

fun View.showView() {
    visibility = View.GONE
}