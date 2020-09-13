package com.gameScheduleApp.ui.calender.schedule

import com.gameScheduleApp.models.ScheduleDisplayData
import com.gameScheduleApp.util.BasePresenter
import com.gameScheduleApp.util.BaseView

class ScheduleContract {
    interface View : BaseView<Presenter> {
        val isActive: Boolean

        fun showSchedule(scheduleDisplayDataList: MutableList<ScheduleDisplayData>)

        fun showStartPosition()
    }

    interface Presenter : BasePresenter {
        fun openSchedule()

        fun openStartPosition()

        fun activeScheduleDetail(scheduleId: Long)
    }
}