package com.interview.payments.presentation.furniture.details

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.interview.payments.R
import com.interview.payments.databinding.FragmentFurnitureDetailsBinding
import com.interview.payments.ext.prepareFurnitureSpannable
import com.interview.payments.presentation.BaseFragment
import com.interview.payments.presentation.furniture.details.adapter.FurniturePreviewAdapter

class FurnitureDetailsFragment : BaseFragment<FragmentFurnitureDetailsBinding>(R.layout.fragment_furniture_details) {

    private val viewModel: FurnitureDetailsViewModel by viewModels()
    private val args: FurnitureDetailsFragmentArgs by navArgs()
    private val furniturePreviewAdapter by lazy { FurniturePreviewAdapter() }

    override fun bind(view: View) = FragmentFurnitureDetailsBinding.bind(view)

    override fun onViewBound(binding: FragmentFurnitureDetailsBinding) = with(binding) {
        viewModel.triggerEvent(FurnitureDetailsViewModel.Event.GetFurniturePreviews)
        furnitureNameACTV.text = args.furniture.title
        furnitureDescriptionACTV.text = args.furniture.description
        furniturePriceACTV.prepareFurnitureSpannable(args.furniture)
        furniturePreviewRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = furniturePreviewAdapter
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
        observeState()
        buyMB.setOnClickListener { navigateToSelectPaymentCard() }
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state -> handleState(state) }
        }
    }

    private fun handleState(state: FurnitureDetailsViewModel.State) = binding?.apply {
        when (state) {
            FurnitureDetailsViewModel.State.LoadingContent -> furniturePreviewProgress.show()
            is FurnitureDetailsViewModel.State.UpdatingContent -> {
                furniturePreviewProgress.hide()
                furniturePreviewAdapter.submitList(state.previews)
            }
        }
    }

    private fun navigateToSelectPaymentCard() =
        navController.navigate(FurnitureDetailsFragmentDirections.toSelectPaymentCard())
}