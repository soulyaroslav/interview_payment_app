package com.interview.payments.presentation.furniture.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.interview.payments.R
import com.interview.payments.databinding.FragmentFurnitureBinding
import com.interview.payments.databinding.FragmentFurnitureDetailsBinding
import com.interview.payments.domain.pojo.Furniture
import com.interview.payments.ext.spaceDecorator
import com.interview.payments.presentation.BaseFragment
import com.interview.payments.presentation.furniture.main.adapter.FurnitureAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FurnitureFragment : BaseFragment<FragmentFurnitureBinding>() {

    private val viewModel: FurnitureViewModel by viewModels()
    private val furnitureAdapter by lazy {
        FurnitureAdapter { navigateToFurnitureDetails(it) }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFurnitureBinding.inflate(inflater, container, false)

    override fun onViewBound(binding: FragmentFurnitureBinding) {
        prepareViews(binding)
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state -> handleState(state) }
        }
    }

    private fun prepareViews(binding: FragmentFurnitureBinding) = with(binding) {
        furnitureRV.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = furnitureAdapter
            spaceDecorator(resources.getDimensionPixelSize(R.dimen.furniture_grid_item_space), SPAN_COUNT)
        }
    }

    private fun handleState(state: FurnitureViewModel.State) = binding?.apply {
        when (state) {
            FurnitureViewModel.State.LoadingContent -> furnitureProgress.show()
            is FurnitureViewModel.State.UpdatingContent -> {
                furnitureProgress.hide()
                furnitureAdapter.submitList(state.furniture)
            }
        }
    }

    private fun navigateToFurnitureDetails(furniture: Furniture) = findNavController()
        .navigate(FurnitureFragmentDirections.toFurnitureDetails(furniture))

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        const val SPAN_COUNT = 2
    }
}
