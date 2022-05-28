package com.interview.payments.presentation

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.interview.payments.R

abstract class BaseBottomSheetDialog<B : ViewBinding>(
    @LayoutRes private val layoutId: Int,
    @DrawableRes private val backgroundId: Int
) : BottomSheetDialogFragment() {

    protected lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    protected var binding: B? = null
    protected val navController by lazy { findNavController() }

    override fun setupDialog(dialog: Dialog, style: Int) {
        dialog.setOnShowListener { prepareBottomSheet(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(requireActivity().layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run { onViewBound(this) }
    }

    abstract fun inflate(inflater: LayoutInflater): B
    abstract fun onViewBound(binding: B)

    private fun prepareBottomSheet(dialog: DialogInterface) {
        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
        val coordinatorLayout = bottomSheet.parent as CoordinatorLayout
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = bottomSheet.height
        bottomSheetBehavior.isHideable = false
        coordinatorLayout.parent.requestLayout()
        if (backgroundId != 0) {
            bottomSheet.background = ContextCompat.getDrawable(
                requireContext(),
                backgroundId
            )
        }
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun navigateUp() = navController.navigateUp()
}