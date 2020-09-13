package com.gameScheduleApp.ui.scheduleDetail

import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.db.schedule.ScheduleRepository

class ScheduleDetailPresenter(
        private val scheduleDetailView: ScheduleDetailContract.View,
        private val scheduleRepository: ScheduleRepository
//    ,
//    private val pref: SharedPreferences
) : ScheduleDetailContract.Presenter {

    init {
        scheduleDetailView.presenter = this
    }

    override fun start() {
//        openScheduleDetail()
    }

    override fun openScheduleDetail(displayData: ScheduleData) {
//        val schedule = scheduleRepository.selectScheduleById()
//        return
    }

}