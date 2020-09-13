package com.gameScheduleApp.ui.calender.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.ScheduleDisplayData
import com.gameScheduleApp.util.db.schedule.ScheduleRepository

class ScheduleFragment : Fragment(), ScheduleContract.View {

    // parts
    private lateinit var gs2001ScheduleRecyclerView: RecyclerView

    override lateinit var presenter: ScheduleContract.Presenter

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
        val view = inflater.inflate(R.layout.gs2001_schedule, container, false)

        // parts
        with(view) {
            gs2001ScheduleRecyclerView = findViewById(R.id.gs2001_schedule_recycler_view)
        }

        // presenter
        presenter = SchedulePresenter(this, ScheduleRepository(view.context))

        return view
    }

    override fun showSchedule(scheduleDisplayDataList: MutableList<ScheduleDisplayData>) {
        // アダプターとレイアウトマネージャーの設定
        with(gs2001ScheduleRecyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ScheduleAdapter(scheduleDisplayDataList, onClickListener)
            setRecycledViewPool(recycledViewPool)
            addItemDecoration(ScheduleDecoration.ScheduleDayDecoration(this.context))
        }
    }

    override fun showStartPosition() {
        with(gs2001ScheduleRecyclerView) {

        }

    }

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    // clickListener
    private var onClickListener: View.OnClickListener = View.OnClickListener {
        val scheduleId = 0L
        presenter.activeScheduleDetail(scheduleId)
        findNavController().navigate(R.id.action_scheduleFragment_to_scheduleDetailFragment)
    }
}