package com.shapee.android.views.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val itemSpacing: Int,
    private val topSpace: Int,
    private val bottomSpace: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when {
            parent.getChildAdapterPosition(view) + 1 == parent.adapter?.itemCount -> {
                outRect.bottom = bottomSpace
            }
            parent.getChildAdapterPosition(view) == 0 -> {
                outRect.top = topSpace
            }
            else -> {
                outRect.bottom = itemSpacing
            }
        }
    }
}