package com.gameScheduleApp.ui.scheduleDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.db.schedule.ScheduleRepository

class ScheduleDetailFragment : Fragment(), ScheduleDetailContract.View {

    private lateinit var gs2002GameTitle: TextView
    private lateinit var gs2002Date: TextView
    private lateinit var gs2002Time: TextView
    private lateinit var gs2002Goal: TextView
    private lateinit var gs2002Description: TextView
    private lateinit var gs2002IconGame: ImageView
    private lateinit var gs2002IconDate: ImageView
    private lateinit var gs2002IconTime: ImageView
    private lateinit var gs2002IconGoal: ImageView
    private lateinit var gs2002IconDescription: ImageView

    override lateinit var presenter: ScheduleDetailContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.gs2002_schedule_detail, container, false)

        // 宣言した各パーツ変数と、画面パーツの結びつけ
        with(view) {
            gs2002GameTitle = findViewById(R.id.gs2002_game_title)
            gs2002Date = findViewById(R.id.gs2002_date)
            gs2002Time = findViewById(R.id.gs2002_time)
            gs2002Goal = findViewById(R.id.gs2002_goal)
            gs2002Description = findViewById(R.id.gs2002_description)
            gs2002IconGame = findViewById(R.id.gs2002_icon_game)
            gs2002IconDate = findViewById(R.id.gs2002_icon_date)
            gs2002IconTime = findViewById(R.id.gs2002_icon_time)
            gs2002IconGoal = findViewById(R.id.gs2002_icon_goal)
            gs2002IconDescription = findViewById(R.id.gs2002_icon_description)
        }

        // prensenterの生成
        presenter = ScheduleDetailPresenter(this, ScheduleRepository(view.context))

        return view
    }

    override fun showScheduleDetail(displayData: ScheduleData) {

    }
}