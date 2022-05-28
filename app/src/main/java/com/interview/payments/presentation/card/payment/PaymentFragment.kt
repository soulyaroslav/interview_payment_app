package com.interview.payments.presentation.card.payment

import android.view.View
import com.interview.payments.R
import com.interview.payments.databinding.FragmentPaymentBinding
import com.interview.payments.presentation.BaseFragment

class PaymentFragment : BaseFragment<FragmentPaymentBinding>(R.layout.fragment_payment) {
    override fun bind(view: View) = FragmentPaymentBinding.bind(view)

    override fun onViewBound(binding: FragmentPaymentBinding) = with(binding) {

    }
}