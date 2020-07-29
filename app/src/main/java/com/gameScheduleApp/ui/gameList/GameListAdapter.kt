package com.gameScheduleApp.ui.gameList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.GameListData
import com.gameScheduleApp.util.DateFormatter

class GameListAdapter(private val dataSet: MutableList<GameListData>) :
    RecyclerView.Adapter<GameListAdapter.GameListViewHolder>() {

    // ViewHolder
    class GameListViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {
        val gsi3001_game_title: TextView
        val gsi3001_game_image: ImageView
        val gsi3001_last_play_date: TextView

        init {
            gsi3001_game_title = v.findViewById(R.id.gsi3001_game_title)
            gsi3001_game_image = v.findViewById(R.id.gsi3001_game_image)
            gsi3001_last_play_date = v.findViewById(R.id.gsi3001_last_play_date)
        }
    }

    // viewHolderの作成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.gsi3001_game_list_item, parent, false)
        return GameListViewHolder(v)
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.gsi3001_game_title.text = dataSet[position].gameTitle
        holder.gsi3001_last_play_date.text =
            DateFormatter().formatPattern1(dataSet[position].lastPlayDate)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}