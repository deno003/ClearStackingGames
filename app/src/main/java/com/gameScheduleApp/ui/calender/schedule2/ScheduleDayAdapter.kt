package com.gameScheduleApp.ui.calender.schedule2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.DateFormatter
import org.threeten.bp.LocalDate

class ScheduleDayAdapter(
    private val dataSet: MutableList<ScheduleData>,
    private val dataSet2: MutableList<ScheduleData>
) :
    RecyclerView.Adapter<ScheduleDayAdapter.ScheduleViewHoder>() {

    // ViewHolder
    class ScheduleViewHoder(private val v: View) : RecyclerView.ViewHolder(v) {
        val gsi2001DayOfTheWeek: TextView
        val gsi2001Day: TextView
        val gsi2001ItemRecyclerView: RecyclerView

        init {
            gsi2001DayOfTheWeek = v.findViewById(R.id.gsi2001_day_of_the_week)
            gsi2001Day = v.findViewById(R.id.gsi2001_day)
            gsi2001ItemRecyclerView = v.findViewById(R.id.gsi2001_item_recycler_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHoder {
        // view新規作成
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.gsi2001_schedule_item_day, parent, false)

        return ScheduleViewHoder(v)
    }

    override fun onBindViewHolder(holder: ScheduleViewHoder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.gsi2001DayOfTheWeek.text = DateFormatter().dayOfWeek(dataSet[position].date)
        holder.gsi2001Day.text = DateFormatter().day(dataSet[position].date)

        // TODO 日付設定無しのスケジュールのソート設定
        val scheduleInSameDay =
            dataSet2.filter { d -> d.date == dataSet[position].date }.sortedBy { it.startTime }
                .toMutableList()

        holder.gsi2001ItemRecyclerView.apply {
            layoutManager = LinearLayoutManager(holder.gsi2001ItemRecyclerView.context)
            adapter = ScheduleItemAdapter(scheduleInSameDay)
            setRecycledViewPool(recycledViewPool)
            isNestedScrollingEnabled = false
            addItemDecoration(ScheduleItemDecoration(context))
        }

        var todaySchedule: ScheduleData =
            dataSet2.filter { it.date == LocalDate.now().toString() }[0]

        var a = holder.itemView

        print(a)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}