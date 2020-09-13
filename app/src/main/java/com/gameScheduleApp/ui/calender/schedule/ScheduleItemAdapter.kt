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

class ScheduleItemAdapter(private val dataSet: MutableList<ScheduleData>, private val onItemClickListener: View.OnClickListener) :
        RecyclerView.Adapter<ScheduleItemAdapter.ScheduleItemViewHolder>() {

//    private var onItemClickListener2:View.OnClickListener = object :View.OnClickListener{
//        override fun onClick(v: View) {
//            val position = v.
//        }
//    }

    lateinit var listener: OnItemClickListener

    // ViewHolder
    class ScheduleItemViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        val gsi2002ScheduleTitle: TextView = v.findViewById(R.id.gsi2001_02_schedule_title)
        val gsi2002Period: TextView = v.findViewById(R.id.gsi2001_02_period)
        val gsi2002Description: TextView = v.findViewById(R.id.gsi2001_02_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        // view新規作成
        val v =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.gsi2001_02_schedule_item, parent, false)

        var holder = ScheduleItemViewHolder(v)
        holder.itemView.setOnClickListener { onItemClickListener.onClick(v) }
        return holder
    }

    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.gsi2002ScheduleTitle.text = dataSet[position].gameTitle
        holder.gsi2002Period.text =
                TimeFormatter().formatPeriod(dataSet[position].startTime, dataSet[position].endTime)
        holder.gsi2002Description.text = dataSet[position].description
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    // インターフェース
    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int, ScheduleId: String)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}
