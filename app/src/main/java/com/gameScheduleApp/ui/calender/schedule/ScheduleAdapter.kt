package com.gameScheduleApp.ui.calender.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ScheduleDisplayData
import com.gameScheduleApp.util.DateFormatter

class ScheduleAdapter(private val dataSet: MutableList<ScheduleDisplayData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_YEAR_MONTH = 0
        private const val VIEW_TYPE_DAY = 1

        // 各ViewHolder
        private class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val gsf2003MonthTitle: TextView = itemView.findViewById(R.id.gsf2003_month_title)

        }

        private class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val gsi2001DayOfTheWeek: TextView = itemView.findViewById(R.id.gsi2001_day_of_the_week)
            val gsi2001Day: TextView = itemView.findViewById(R.id.gsi2001_day)
            val gsi2001ItemRecyclerView: RecyclerView =
                itemView.findViewById(R.id.gsi2001_item_recycler_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // view新規作成
        var view: View? = null

        when (viewType) {
            // 月
            VIEW_TYPE_YEAR_MONTH -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.gsi2003_month_title, parent, false)
                return MonthViewHolder(view)
            }
            // 日・スケジュール
            VIEW_TYPE_DAY -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.gsi2001_schedule_item_day, parent, false)
                return DayViewHolder(view)
            }
        }

        return DayViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // viewTypeごとに値をbind
        when (getItemViewType(position)) {
            VIEW_TYPE_YEAR_MONTH -> {
                holder as MonthViewHolder
                holder.gsf2003MonthTitle.text = DateFormatter().yearMonth(dataSet[position].date)
            }
            VIEW_TYPE_DAY -> {
                holder as DayViewHolder
                holder.gsi2001DayOfTheWeek.text = DateFormatter().dayOfWeek(dataSet[position].date)
                holder.gsi2001Day.text = DateFormatter().day(dataSet[position].date)
                holder.gsi2001ItemRecyclerView.apply {
                    layoutManager = LinearLayoutManager(holder.gsi2001ItemRecyclerView.context)
                    adapter = ScheduleItemAdapter(dataSet[position].scheduleData!!)
                    setRecycledViewPool(recycledViewPool)
                    isNestedScrollingEnabled = false
                    addItemDecoration(ScheduleDecoration.ScheduleItemDecoration(context))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    // viewType判定
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].displayCategory
    }
}
