package com.gameScheduleApp.ui.calender.schedule

import com.gameScheduleApp.models.ScheduleDisplayData
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
        // 返却用MutableList
        var displayDataList = mutableListOf<ScheduleDisplayData>()

        // スケジュールデータ取得
        var scheduleData = scheduleRepository.selectAll()

        // 月次schedule追加
        var endCalenderDate: LocalDate = LocalDate.parse("2100-01-01")
        var date: LocalDate = LocalDate.parse("2019-01-01")
        // 2100年1月までループ処理
        while (!(date.year == endCalenderDate.year && date.month == endCalenderDate.month)) {
            // displayData
            var scheduleDisplayData: ScheduleDisplayData =
                ScheduleDisplayData(
                    displayCategory = 0,
                    date = date.toString(),
                    scheduleData = null
                )

            displayDataList.add(scheduleDisplayData)
            date = date.plusMonths(1L)
        }

        // 日時schedule追加
        var scheduleDay = scheduleData.distinctBy { it.date }.toMutableList()
        for (schedule in scheduleDay) {

            // 同日のスケジュールを抽出
            var scheduleDataOfTheDay =
                scheduleData.filter { it.date == schedule.date }.sortedBy { it.startTime }
                    .toMutableList()

            // displayData
            var scheduleDisplayData: ScheduleDisplayData =
                ScheduleDisplayData(
                        displayCategory = 1,
                        date = schedule.date,
                        scheduleData = scheduleDataOfTheDay
                )
            displayDataList.add(scheduleDisplayData)
        }

        return displayDataList.sortedWith(compareBy({ it.date }, { it.displayCategory }))
                .toMutableList()
    }

    override fun activeScheduleDetail(scheduleId: Long) {
//        var schedule = scheduleRepository.selectScheduleById(scheduleId)

    }
}