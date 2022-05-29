package com.interview.payments.domain.usecase

import com.interview.payments.R
import com.interview.payments.domain.pojo.FieldType
import com.interview.payments.domain.pojo.ValidationResult
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class IsPaymentCardValidUseCase @Inject constructor(): UseCaseParams<ValidationResult, IsPaymentCardValidUseCase.Params> {
    class Params private constructor(val number: String, val expirationDay: String, val secureCode: String, val holderName: String) {
        companion object {
            fun toParams(number: String, expirationDay: String, secureCode: String, holderName: String) =
                Params(number, expirationDay, secureCode, holderName)
        }
    }

    override suspend fun execute(params: Params): ValidationResult = with(params) {
        when {
            number.isEmpty() -> ValidationResult.Invalid(FieldType.CARD_NUMBER, R.string.card_number_validation_error)
            number.length < MAX_CARD_LENGTH -> ValidationResult.Invalid(FieldType.CARD_NUMBER, R.string.card_legth_validation_error)
            !isCardDateValid(expirationDay) -> ValidationResult.Invalid(FieldType.EXPIRY_DATE, R.string.expiry_date_format_validation_error)
            expirationDay.isEmpty() -> ValidationResult.Invalid(FieldType.EXPIRY_DATE, R.string.expiration_day_validation_error)
            secureCode.isEmpty() -> ValidationResult.Invalid(FieldType.SECURE_CODE, R.string.secure_code_validation_error)
            secureCode.length < MAX_CARD_SECURE_CODE_LENGTH -> ValidationResult.Invalid(
                FieldType.SECURE_CODE,
                R.string.secure_code_length_validation_error
            )
            holderName.isEmpty() -> ValidationResult.Invalid(FieldType.HOLDER_NAME, R.string.holder_name_validation_error)
            else -> ValidationResult.Valid
        }
    }

    private fun isCardDateValid(date: String): Boolean {
        val dateFormat: DateFormat = SimpleDateFormat(CARD_DATE_FORMAT, Locale.ENGLISH)
        dateFormat.isLenient = false
        try {
            dateFormat.parse(date)
        } catch (e: ParseException) {
            return false
        }
        return true
    }

    companion object {
        const val CARD_DATE_FORMAT = "MM/dd"
        const val MAX_CARD_LENGTH = 19
        const val MAX_CARD_SECURE_CODE_LENGTH = 3
    }
}