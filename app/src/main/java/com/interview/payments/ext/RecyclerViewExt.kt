package com.interview.payments.ext

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.spaceDecorator(itemOffset: Int, spanCount: Int, includeEdge: Boolean = true) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            val position: Int = parent.getChildAdapterPosition(view) // item position
            val column: Int = position % spanCount // item column


            if (includeEdge) {
                outRect.left = itemOffset - column * itemOffset / spanCount
                outRect.right = (column + 1) * itemOffset / spanCount
                if (position < spanCount) {
                    outRect.top = itemOffset
                }
                outRect.bottom = itemOffset
            } else {
                outRect.left = column * itemOffset / spanCount
                outRect.right = itemOffset - (column + 1) * itemOffset / spanCount
                if (position >= spanCount) {
                    outRect.top = itemOffset
                }
            }
        }
    })
}