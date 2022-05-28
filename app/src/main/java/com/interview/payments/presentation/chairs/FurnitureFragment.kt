package com.interview.payments.presentation.chairs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.interview.payments.R
import com.interview.payments.databinding.FragmentFurnitureBinding
import com.interview.payments.ext.spaceDecorator
import com.interview.payments.presentation.chairs.adapter.FurnitureAdapter

class FurnitureFragment : Fragment(R.layout.fragment_furniture) {

    private var binding: FragmentFurnitureBinding? = null
    private val viewModel: FurnitureViewModel by viewModels()
    private val furnitureAdapter by lazy { FurnitureAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFurnitureBinding.bind(view)
        prepareViews()
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state -> handleState(state) }
        }
    }

    private fun prepareViews() = binding?.apply {
        furnitureRV.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = furnitureAdapter
            spaceDecorator(resources.getDimensionPixelSize(R.dimen.furniture_grid_item_space), SPAN_COUNT)
        }
    }

    private fun handleState(state: FurnitureState) = binding?.apply {
        when (state) {
            FurnitureState.LoadingContent -> furnitureProgress.show()
            is FurnitureState.UpdatingContent -> {
                furnitureProgress.hide()
                furnitureAdapter.submitList(state.furniture)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        const val SPAN_COUNT = 2
    }
}
