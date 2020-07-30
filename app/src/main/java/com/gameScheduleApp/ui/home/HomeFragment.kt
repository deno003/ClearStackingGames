package com.gameScheduleApp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ActivityHistoryBaseRowData
import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.ui.activity.ActivityHistoryBaseAdapter
import com.gameScheduleApp.util.DateFormatter
import com.gameScheduleApp.util.TimeFormatter
import com.gameScheduleApp.util.db.game_list.GameListRepository
import com.gameScheduleApp.util.db.schedule.ScheduleRepository
import kotlinx.android.synthetic.main.gs1001_home.view.*

/**
 * GS1001_homeFragment
 */
class HomeFragment : Fragment(), HomeContract.View {

    // 属性
    private lateinit var gsf0001_date: TextView
    private lateinit var gsf0001_activity: TextView
    private lateinit var gsf0001_period: TextView
    private lateinit var gs1001Activity: RecyclerView

    // presenter
    override lateinit var presenter: HomeContract.Presenter

    override val isActive: Boolean
        get() = isAdded

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.gs1001_home, container, false)

        with(view) {
            gsf0001_date = findViewById(R.id.gsf0001_date)
            gsf0001_activity = findViewById(R.id.gsf0001_activity)
            gsf0001_period = findViewById(R.id.gsf0001_period)
            gs1001Activity = findViewById(R.id.gs1001_activity)
        }

        // Presenter
        presenter =
            HomePresenter(this, ScheduleRepository(view.context), GameListRepository(view.context))

        // ボタン押下時の画面遷移設定
        with(view) {
            gs0001_game_list_button.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
            }
            gs0001_schedule_button.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_scheduleFragment2)
            }
        }

        return view
    }

    // 今日の予定設定
    override fun showTodaySchedule(todaySchedule: ScheduleData) {
        gsf0001_date.text = DateFormatter().todayPattern2()
        gsf0001_activity.text = todaySchedule.gameTitle
        gsf0001_period.text =
            TimeFormatter().formatPeriod(todaySchedule.startTime, todaySchedule.endTime)
    }

    // アクティビティ表示
    override fun showActivity(activityList: MutableList<ActivityHistoryBaseRowData>) {
        // アダプターとレイアウトマネージャーの設定
        with(gs1001Activity) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ActivityHistoryBaseAdapter(activityList)
        }
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}