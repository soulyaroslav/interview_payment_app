package com.interview.payments.domain.usecase

import android.content.Context
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.text.*
import com.interview.payments.PaymentApplication
import com.interview.payments.R
import com.interview.payments.data.repository.FurnitureRepositoryImpl
import com.interview.payments.domain.repository.FurnitureRepository
import com.interview.payments.presentation.chairs.adapter.FurnitureItem

class GetFurnitureUseCase(
    private val context: Context = PaymentApplication.appContext,
    private val repository: FurnitureRepository = FurnitureRepositoryImpl()
) : UseCase<List<FurnitureItem>> {
    override suspend fun execute(): List<FurnitureItem> = repository.getFurniture()
        .map { furniture ->
            val newPrice = furniture.discountPrice?.let { discount ->
                buildSpannedString {
                    backgroundColor(ContextCompat.getColor(context, R.color.progress_bar)) {
                        append("$")
                        append(discount.toString())
                    }
                    append(" ")
                    strikeThrough {
                        scale(.6f) {
                            inSpans(ForegroundColorSpan(ContextCompat.getColor(context, android.R.color.holo_red_dark))) {
                                append("$")
                                append(furniture.price.toString())
                            }
                        }
                    }
                }
            } ?: SpannedString("${furniture.price}\$")

            FurnitureItem(furniture.id, furniture.title, furniture.description, newPrice, furniture.image)
        }
}

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}