package com.interview.payments.ext

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

private val nonDigits = Regex("[^\\d]")
private const val CARD_LENGTH = 16
private const val CARD_CHUNK_LENGTH = 4

fun TextInputEditText.addPaymentCardTextWatcher(afterTextChanged: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        private var current = ""
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            error = null
        }

        override fun afterTextChanged(s: Editable) {
            if (s.toString() != current) {
                val userInput = s.toString().replace(nonDigits, "")
                if (userInput.length <= CARD_LENGTH) {
                    current = userInput.chunked(CARD_CHUNK_LENGTH).joinToString(" ")
                    s.filters = arrayOfNulls(0)
                }
                s.replace(0, s.length, current, 0, current.length)
            }
            afterTextChanged(s.toString())
        }
    })
}

fun TextInputEditText.addPaymentExpiryDateTextWatcher(afterTextChanged: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        private var current = ""
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            if (s.toString() != current) {
                val userInput = s.toString().replace(nonDigits, "")
                if (userInput.length <= 4) {
                    current = userInput.chunked(2).joinToString("/")
                    s.filters = arrayOfNulls(0)
                }
                s.replace(0, s.length, current, 0, current.length)
                afterTextChanged(s.toString())
            }
        }
    })
}

fun TextInputEditText.addTextWatcher(afterTextChanged: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {
            afterTextChanged(s.toString())
        }
    })
}