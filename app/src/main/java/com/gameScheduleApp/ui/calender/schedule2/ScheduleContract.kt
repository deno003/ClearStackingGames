package com.gameScheduleApp.ui.calender.schedule2

import com.gameScheduleApp.util.BasePresenter
import com.gameScheduleApp.util.BaseView

class ScheduleContract {
    interface View : BaseView<Presenter> {
        val isActive: Boolean

        fun showSchedule(scheduleDisplayDataList: MutableList<ScheduleDisplayData2>)

        fun showStartPosition()
    }

    interface Presenter : BasePresenter {
        fun openSchedule()

        fun openStartPosition()
    }
}