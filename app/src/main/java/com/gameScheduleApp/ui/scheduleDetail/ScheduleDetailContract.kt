package com.gameScheduleApp.ui.scheduleDetail

import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.BasePresenter
import com.gameScheduleApp.util.BaseView

class ScheduleDetailContract {
    interface View : BaseView<ScheduleDetailContract.Presenter> {
        val isActive: Boolean

        fun showScheduleDetail(displayData: ScheduleData)
    }

    interface Presenter : BasePresenter {
        fun openScheduleDetail(displayData: ScheduleData)
    }
}