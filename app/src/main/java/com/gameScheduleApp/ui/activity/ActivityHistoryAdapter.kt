package com.gameScheduleApp.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ActivityHistoryRowData

class ActivityHistoryAdapter(private val dataSet: MutableList<ActivityHistoryRowData>) :
    RecyclerView.Adapter<ActivityHistoryAdapter.ActivityHistoryViewHolder>() {

    // ViewHolder
    class ActivityHistoryViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        val gsf0003_activity: TextView
        val gsf0003_last_week_play_time: TextView
        val gsf0003_total_play_time: TextView

        init {
            // クリックリスナーの定義なんかもここでやる
            gsf0003_activity = v.findViewById(R.id.gsf0003_activity)
            gsf0003_last_week_play_time = v.findViewById(R.id.gsf0003_last_week_play_time)
            gsf0003_total_play_time = v.findViewById(R.id.gsf0003_total_play_time)
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ActivityHistoryViewHolder {
        // viewの新規作成
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.gsi1001_activity_list_item, viewGroup, false)

        return ActivityHistoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: ActivityHistoryViewHolder, position: Int) {
        holder.gsf0003_activity.text =
            dataSet[position].activityContent
        holder.gsf0003_last_week_play_time.text = dataSet[position].playTimeInAWeek
        holder.gsf0003_total_play_time.text = dataSet[position].totalPlayTime

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
