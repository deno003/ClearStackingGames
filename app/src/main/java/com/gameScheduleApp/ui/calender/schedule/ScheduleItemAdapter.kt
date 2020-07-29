package com.gameScheduleApp.ui.calender.schedule

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.TimeFormatter

class ScheduleItemAdapter(private val dataSet: MutableList<ScheduleData>) :
    RecyclerView.Adapter<ScheduleItemAdapter.ScheduleItemViewHolder>() {

    // ViewHolder
    class ScheduleItemViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        val gsi2002_schedule_title: TextView
        val gsi2002_period: TextView
        val gsi2002_description: TextView

        init {
            gsi2002_schedule_title = v.findViewById(R.id.gsi2002_schedule_title)
            gsi2002_period = v.findViewById(R.id.gsi2002_period)
            gsi2002_description = v.findViewById(R.id.gsi2002_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        // view新規作成
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gsi2002_schedule_item, parent, false)

        return ScheduleItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.gsi2002_schedule_title.text = dataSet[position].gameTitle
        holder.gsi2002_period.text =
            TimeFormatter().formatPeriod(dataSet[position].startTime, dataSet[position].endTime)
        holder.gsi2002_description.text = dataSet[position].description
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
