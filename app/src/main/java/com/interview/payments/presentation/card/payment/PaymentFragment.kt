package com.interview.payments.presentation.card.payment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.interview.payments.R
import com.interview.payments.databinding.FragmentPaymentBinding
import com.interview.payments.domain.pojo.FieldType
import com.interview.payments.domain.pojo.ValidationResult
import com.interview.payments.ext.addPaymentCardTextWatcher
import com.interview.payments.ext.addPaymentExpiryDateTextWatcher
import com.interview.payments.ext.addTextWatcher
import com.interview.payments.ext.hideKeyboard
import com.interview.payments.presentation.BaseFragment

class PaymentFragment : BaseFragment<FragmentPaymentBinding>(R.layout.fragment_payment) {
    private val viewModel: PaymentViewModel by viewModels()

    override fun bind(view: View) = FragmentPaymentBinding.bind(view)

    override fun onViewBound(binding: FragmentPaymentBinding) = with(binding) {
        cardNumberTIET.addPaymentCardTextWatcher {
            cardPreviewACTV.text = it
        }
        expiryDateTIET.addPaymentExpiryDateTextWatcher {
            cardExpiryPreviewACTV.text = it
        }
        cardHolderTIET.addTextWatcher {
            cardHolderPreviewACTV.text = it
        }
        paymentMB.setOnClickListener {
            val number = cardNumberTIET.text.toString()
            val expirationDay = expiryDateTIET.text.toString()
            val secureCode = secureCodeTIET.text.toString()
            val holderName = cardHolderTIET.text.toString()
            viewModel.validatePaymentCard(number, expirationDay, secureCode, holderName)
        }
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state -> handleState(state) }
        }
    }

    private fun handleState(state: PaymentViewModel.State) = binding?.apply {
        when (state) {
            PaymentViewModel.State.LoadingContent -> {
            }
            is PaymentViewModel.State.ValidationPayment -> {
                when (state.validationResult) {
                    is ValidationResult.Invalid -> showValidationErrors(state.validationResult)
                    ValidationResult.Valid -> {
                        cardNumberTIL.error = null
                        expiryDateTIL.error = null
                        secureCodeTIL.error = null
                        cardHolderTIL.error = null
                    }
                }
            }
        }
    }

    private fun showValidationErrors(result: ValidationResult.Invalid) = binding?.apply {
        when (result.type) {
            FieldType.CARD_NUMBER -> {
                cardNumberTIL.error = requireContext().getString(result.error)
                expiryDateTIL.error = null
                secureCodeTIL.error = null
                cardHolderTIL.error = null
            }
            FieldType.EXPIRY_DATE -> {
                expiryDateTIL.error = requireContext().getString(result.error)
                cardNumberTIL.error = null
                secureCodeTIL.error = null
                cardHolderTIL.error = null
            }
            FieldType.SECURE_CODE -> {
                secureCodeTIL.error = requireContext().getString(result.error)
                cardNumberTIL.error = null
                expiryDateTIL.error = null
                cardHolderTIL.error = null
            }
            FieldType.HOLDER_NAME -> {
                cardHolderTIL.error = requireContext().getString(result.error)
                cardNumberTIL.error = null
                secureCodeTIL.error = null
                expiryDateTIL.error = null
            }
        }
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }
}