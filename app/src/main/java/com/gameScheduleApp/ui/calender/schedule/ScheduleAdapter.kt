package com.gameScheduleApp.ui.calender.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ScheduleDisplayData2
import com.gameScheduleApp.util.DateFormatter

class ScheduleAdapter(private val dataSet: MutableList<ScheduleDisplayData2>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_YEAR_MONTH = 0
        private const val VIEW_TYPE_DAY = 1

        // 各ViewHolder
        private class monthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val gsf2003_month_title: TextView

            init {
                gsf2003_month_title = itemView.findViewById(R.id.gsf2003_month_title)
            }
        }

        private class dayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val gsi2001DayOfTheWeek: TextView
            val gsi2001Day: TextView
            val gsi2001ItemRecyclerView: RecyclerView

            init {
                gsi2001DayOfTheWeek = itemView.findViewById(R.id.gsi2001_day_of_the_week)
                gsi2001Day = itemView.findViewById(R.id.gsi2001_day)
                gsi2001ItemRecyclerView = itemView.findViewById(R.id.gsi2001_item_recycler_view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // view新規作成
        var view: View? = null

        when (viewType) {
            VIEW_TYPE_YEAR_MONTH -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.gsi2003_month_title, parent, false)
                return monthViewHolder(view)
            }
            VIEW_TYPE_DAY -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.gsi2001_schedule_item_day, parent, false)
                return dayViewHolder(view)
            }
        }

        return dayViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_YEAR_MONTH -> {
                holder as monthViewHolder
                holder.gsf2003_month_title.text = DateFormatter().yearMonth(dataSet[position].date)
            }
            VIEW_TYPE_DAY -> {
                holder as dayViewHolder
                holder.gsi2001DayOfTheWeek.text = DateFormatter().dayOfWeek(dataSet[position].date)
                holder.gsi2001Day.text = DateFormatter().day(dataSet[position].date)

                holder.gsi2001ItemRecyclerView.apply {
                    layoutManager = LinearLayoutManager(holder.gsi2001ItemRecyclerView.context)
                    adapter = ScheduleItemAdapter(dataSet[position].scheduleData!!)
                    setRecycledViewPool(recycledViewPool)
                    isNestedScrollingEnabled = false
                    addItemDecoration(ScheduleItemDecoration(context))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].displayCategory
    }
}
