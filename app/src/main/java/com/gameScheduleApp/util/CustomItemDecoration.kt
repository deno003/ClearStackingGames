package com.gameScheduleApp.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CustomItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    val horizontalMargin by lazy {
        context.resources.getDimensionPixelOffset(R.dimen.margin_small)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = horizontalMargin
    }

}