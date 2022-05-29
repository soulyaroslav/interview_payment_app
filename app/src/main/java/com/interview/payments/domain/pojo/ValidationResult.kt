package com.interview.payments.domain.pojo

import androidx.annotation.StringRes

sealed class ValidationResult {
    object Valid: ValidationResult()
    data class Invalid(val type: FieldType, @StringRes val error: Int): ValidationResult()
}

enum class FieldType {
    CARD_NUMBER, EXPIRY_DATE, SECURE_CODE, HOLDER_NAME
}
