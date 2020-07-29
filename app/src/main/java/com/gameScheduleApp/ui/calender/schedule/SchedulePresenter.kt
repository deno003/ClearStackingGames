package com.gameScheduleApp.ui.calender.schedule

import com.gameScheduleApp.models.ScheduleDisplayData
import com.gameScheduleApp.util.DateFormatter
import com.gameScheduleApp.util.db.schedule.ScheduleRepository
import org.threeten.bp.LocalDate

class SchedulePresenter(
    private val scheduleView: ScheduleContract.View,
    private val scheduleRepository: ScheduleRepository
) : ScheduleContract.Presenter {
    init {
        scheduleView.presenter = this
    }

    override fun start() {
        openSchedule()
        openStartPosition()
    }

    override fun openSchedule() {
        with(scheduleView) {
            showSchedule(createDisplayScheduleData())
        }
    }

    override fun openStartPosition() {
        with(scheduleView) {
            showStartPosition()
        }
    }

    private fun createDisplayScheduleData(): MutableList<ScheduleDisplayData> {
        var scheduleData = scheduleRepository.selectAll()
        var displayDataList = mutableListOf<ScheduleDisplayData>()
        var end_calender_date: LocalDate = LocalDate.parse("2100-01-01")
        var date: LocalDate = LocalDate.parse("2019-01-01")

        // 2100年1月までループ処理
        while (!(date.year == end_calender_date.year && date.month == end_calender_date.month)) {
            // 画面表示用年月
            var displayDate = DateFormatter().yearMonth(date.toString())
            // 年月ごとのスケジュール
            var scheduleDataInMonth = scheduleData.filter {
                LocalDate.parse(it.date).year == date.year && LocalDate.parse(it.date).month == date.month
            }.toMutableList()

            // displayData
            var scheduleDisplayData: ScheduleDisplayData =
                ScheduleDisplayData(yearMonth = displayDate, scheduleData = scheduleDataInMonth)

            displayDataList.add(scheduleDisplayData)

            date = date.plusMonths(1L)
        }

        return displayDataList
    }
}