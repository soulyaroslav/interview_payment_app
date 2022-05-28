package com.interview.payments.presentation

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding>(@LayoutRes layoutId: Int = 0) : Fragment(layoutId) {

    protected var binding: B? = null
    protected val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)
        binding?.run { onViewBound(this) }
    }

    abstract fun bind(view: View): B
    abstract fun onViewBound(binding: B)

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}