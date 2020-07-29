package com.gameScheduleApp.ui.calender.schedule

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ScheduleDisplayData

class ScheduleMonthAdapter(private val dataSet: MutableList<ScheduleDisplayData>) :
    RecyclerView.Adapter<ScheduleMonthAdapter.ScheduleViewHoder>() {

    // ViewHolder
    class ScheduleViewHoder(private val v: View) : RecyclerView.ViewHolder(v) {
        val gsf2003_month_title: TextView
        val gsi2003_item_recycler_view: RecyclerView

        init {
            gsf2003_month_title = v.findViewById(R.id.gsf2003_month_title)
            gsi2003_item_recycler_view = v.findViewById(R.id.gsi2003_item_recycler_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHoder {
        // view新規作成
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.gsi2003_month_title, parent, false)

        return ScheduleViewHoder(v)
    }

    override fun onBindViewHolder(holder: ScheduleViewHoder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.gsf2003_month_title.text = dataSet[position].yearMonth

        // 日付確保用のリストw;
        val distinctScheduleData =
            dataSet[position].scheduleData.distinctBy { it.date }.sortedBy { it.date }
                .toMutableList()

        var childLayoutManager = LinearLayoutManager(holder.gsi2003_item_recycler_view.context)
        holder.gsi2003_item_recycler_view.apply {
            layoutManager = childLayoutManager
            adapter = ScheduleDayAdapter(distinctScheduleData, dataSet[position].scheduleData)
            setRecycledViewPool(recycledViewPool)
            isNestedScrollingEnabled = false
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}