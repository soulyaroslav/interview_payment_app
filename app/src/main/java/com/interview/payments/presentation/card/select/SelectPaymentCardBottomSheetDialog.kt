package com.interview.payments.presentation.card.select

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.interview.payments.R
import com.interview.payments.databinding.DialogSelectPaymentCardBinding
import com.interview.payments.domain.pojo.Card
import com.interview.payments.ext.spaceDecorator
import com.interview.payments.presentation.BaseBottomSheetDialog
import com.interview.payments.presentation.card.select.adapter.CardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectPaymentCardBottomSheetDialog : BaseBottomSheetDialog<DialogSelectPaymentCardBinding>(
    layoutId = R.layout.dialog_select_payment_card,
    backgroundId = R.drawable.bg_white_corners_top_10
) {
    private val viewModel: SelectPaymentCardViewModel by viewModels()
    private val cardAdapter by lazy {
        CardAdapter { navigateToPayment(it) }
    }

    override fun inflate(inflater: LayoutInflater) =
        DialogSelectPaymentCardBinding.inflate(inflater)

    override fun onViewBound(binding: DialogSelectPaymentCardBinding) = with(binding) {
        cardRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cardAdapter
            spaceDecorator(resources.getDimensionPixelSize(R.dimen.size_15))
        }
        addNewCardMB.setOnClickListener { navigateToPayment() }
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect { state -> handleState(state) }
        }
    }

    private fun handleState(state: SelectPaymentCardViewModel.State) {
        when (state) {
            SelectPaymentCardViewModel.State.LoadingContent -> { }
            is SelectPaymentCardViewModel.State.UpdatingContent -> {
                cardAdapter.submitList(state.cards)
            }
        }
    }

    private fun navigateToPayment(card: Card? = null) = navController
        .navigate(SelectPaymentCardBottomSheetDialogDirections.toPayment(card))
}