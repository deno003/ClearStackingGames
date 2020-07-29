package com.gameScheduleApp.ui.home

import com.gameScheduleApp.models.ActivityHistoryBaseRowData
import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.BasePresenter
import com.gameScheduleApp.util.BaseView

class HomeContract {
    interface View : BaseView<Presenter> {
        val isActive: Boolean

        fun showTodaySchedule(todayScheduleDat: ScheduleData)

        fun showActivity(activityList: MutableList<ActivityHistoryBaseRowData>)
    }

    interface Presenter : BasePresenter {
        fun openTodaySchedule()

        fun openActivity()
    }
}