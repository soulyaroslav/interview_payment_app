package com.interview.payments.presentation.card.payment

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.interview.payments.R
import com.interview.payments.databinding.FragmentPaymentBinding
import com.interview.payments.domain.pojo.FieldType
import com.interview.payments.domain.pojo.ValidationResult
import com.interview.payments.ext.*
import com.interview.payments.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : BaseFragment<FragmentPaymentBinding>() {
    private val viewModel: PaymentViewModel by viewModels()
    private val args: PaymentFragmentArgs by navArgs()

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) = FragmentPaymentBinding.inflate(inflater, container, false)

    override fun onViewBound(binding: FragmentPaymentBinding) = with(binding) {
        prepareViewsByArgs()
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

    private fun prepareViewsByArgs() = binding?.apply {
        args.card?.number?.let {
            cardNumberTIET.setText(it)
            cardPreviewACTV.text = it
        }
        args.card?.expirationDay?.let {
            expiryDateTIET.setText(it)
            cardExpiryPreviewACTV.text = it
        }
        args.card?.holderName?.let {
            cardHolderTIET.setText(it)
            cardHolderPreviewACTV.text = it
        }
        args.card?.secureCode?.let {
            secureCodeTIET.setText(it)
        }
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state -> handleState(state) }
        }
    }

    private fun handleState(state: PaymentViewModel.State) = binding?.apply {
        when (state) {
            PaymentViewModel.State.LoadingContent -> showPaymentProgress()
            is PaymentViewModel.State.ValidationPayment -> {
                when (state.validationResult) {
                    is ValidationResult.Invalid -> {
                        hidePaymentProgress()
                        showValidationErrors(state.validationResult)
                    }
                    ValidationResult.Valid -> {
                        val number = cardNumberTIET.text.toString()
                        val expirationDay = expiryDateTIET.text.toString()
                        val secureCode = secureCodeTIET.text.toString()
                        val holderName = cardHolderTIET.text.toString()
                        viewModel.makePayment(number, expirationDay, secureCode, holderName)
                        cardNumberTIL.error = null
                        expiryDateTIL.error = null
                        secureCodeTIL.error = null
                        cardHolderTIL.error = null
                    }
                }
            }
            is PaymentViewModel.State.PaymentError -> {
                hidePaymentProgress()
                showAlert(state.error)
            }
            PaymentViewModel.State.PaymentSuccess -> {
                hidePaymentProgress()
                showAlert(getString(R.string.success_payment_message))
            }
            PaymentViewModel.State.Init -> hidePaymentProgress()
        }
    }

    private fun showPaymentProgress() = binding?.apply {
        paymentMB.text = ""
        paymentMB.isClickable = false
        paymentProgress.show()
    }

    private fun hidePaymentProgress() = binding?.apply {
        paymentMB.text = requireContext().getString(R.string.pay_title)
        paymentMB.isClickable = true
        paymentProgress.hide()
    }

    private fun showValidationErrors(result: ValidationResult.Invalid) = binding?.apply {
        result.type.hideErrors(binding)
        when (result.type) {
            FieldType.CARD_NUMBER -> cardNumberTIL.error = requireContext().getString(result.error)
            FieldType.EXPIRY_DATE -> expiryDateTIL.error = requireContext().getString(result.error)
            FieldType.SECURE_CODE -> secureCodeTIL.error = requireContext().getString(result.error)
            FieldType.HOLDER_NAME -> cardHolderTIL.error = requireContext().getString(result.error)
        }
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setTitle(R.string.alert_dialog_title)
            .show()
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }
}

fun FieldType.hideErrors(binding: FragmentPaymentBinding?) = binding?.apply {
    when (this@hideErrors) {
        FieldType.CARD_NUMBER -> {
            expiryDateTIL.error = null
            secureCodeTIL.error = null
            cardHolderTIL.error = null
        }
        FieldType.EXPIRY_DATE -> {
            cardNumberTIL.error = null
            secureCodeTIL.error = null
            cardHolderTIL.error = null
        }
        FieldType.SECURE_CODE -> {
            cardNumberTIL.error = null
            expiryDateTIL.error = null
            cardHolderTIL.error = null
        }
        FieldType.HOLDER_NAME -> {
            cardNumberTIL.error = null
            secureCodeTIL.error = null
            expiryDateTIL.error = null
        }
    }
}