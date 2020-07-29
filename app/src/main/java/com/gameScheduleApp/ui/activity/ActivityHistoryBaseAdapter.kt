package com.gameScheduleApp.ui.activity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ActivityHistoryBaseRowData

class ActivityHistoryBaseAdapter(private val dataSet: MutableList<ActivityHistoryBaseRowData>) :
    RecyclerView.Adapter<ActivityHistoryBaseAdapter.ActivityHistoryBaseViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    // ViewHolder
    class ActivityHistoryBaseViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val gsf0002_date: TextView
        val gsf0002_activity_recycler_view: RecyclerView

        init {
            // クリックリスナーの定義なんかもここでやる
            gsf0002_date = v.findViewById(R.id.gsf0002_date)
            gsf0002_activity_recycler_view = v.findViewById(R.id.gsf0002_activity_recycler_view)
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ActivityHistoryBaseViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
        // viewの新規作成
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.gsf1002_activity_history, viewGroup, false)

        return ActivityHistoryBaseViewHolder(v)
    }

    override fun onBindViewHolder(holder: ActivityHistoryBaseViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.gsf0002_date.text = dataSet[position].date

        var childLayoutManager = LinearLayoutManager(holder.gsf0002_activity_recycler_view.context)
        holder.gsf0002_activity_recycler_view.apply {
            layoutManager = childLayoutManager
            adapter = ActivityHistoryAdapter(dataSet[position].activityHistoryContentList)
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
