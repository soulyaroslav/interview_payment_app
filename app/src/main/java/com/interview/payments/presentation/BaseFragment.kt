package com.interview.payments.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    protected var binding: B? = null
    protected val navController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate(inflater, container)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run { onViewBound(this) }
    }

    abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun onViewBound(binding: B)

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}