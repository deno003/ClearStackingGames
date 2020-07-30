package com.gameScheduleApp.ui.calender.schedule

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ScheduleDecoration() : RecyclerView.ItemDecoration() {

    class ScheduleItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.bottom = context.resources.getDimensionPixelOffset(R.dimen.margin_small)
        }
    }

    class ScheduleDayDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val position = parent.getChildAdapterPosition(view)
            var viewType = parent.adapter!!.getItemViewType(position)
            if (viewType == 1) {
                outRect.top = context.resources.getDimensionPixelOffset(R.dimen.margin_small)
            }
        }
    }
}