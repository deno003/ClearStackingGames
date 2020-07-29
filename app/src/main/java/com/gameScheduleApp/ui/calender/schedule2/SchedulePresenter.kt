package com.gameScheduleApp.ui.calender.schedule2

import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.models.ScheduleDisplayData2
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

    private fun createDisplayScheduleData(): MutableList<ScheduleDisplayData2> {
        // 返却用MutableList
        var displayDataList = mutableListOf<ScheduleDisplayData2>()

        // スケジュールデータ取得
        var scheduleData = scheduleRepository.selectAll()
        // display用にマッピング
        for (schedule in scheduleData) {
            val scheduleDisplayData = schedule.toScheduleDisplayData()
            displayDataList.add(scheduleDisplayData)
        }

        // 月次schedule追加
        var endCalenderDate: LocalDate = LocalDate.parse("2100-01-01")
        var date: LocalDate = LocalDate.parse("2019-01-01")

        // 2100年1月までループ処理
        while (!(date.year == endCalenderDate.year && date.month == endCalenderDate.month)) {
            // displayData
            var scheduleDisplayData: ScheduleDisplayData2 =
                ScheduleDisplayData2(displayCategory = 0, date = date.toString())

            displayDataList.add(scheduleDisplayData)

            date = date.plusMonths(1L)
        }

        // 日時schedule追加
        var scheduleDay = scheduleData.distinctBy { it.date }.toMutableList()
        for (schedule in scheduleDay) {
            // displayData
            var scheduleDisplayData: ScheduleDisplayData2 =
                ScheduleDisplayData2(displayCategory = 1, date = schedule.date)
            displayDataList.add(scheduleDisplayData)

        }

        var a = displayDataList.sortedBy { it.date }.sortedBy { it.displayCategory }.toMutableList()

        return displayDataList.sortedBy { it.date }.sortedBy { it.displayCategory }.toMutableList()
    }

    private fun ScheduleData.toScheduleDisplayData() =
        ScheduleDisplayData2(
            displayCategory = 2,
            scheduleId = scheduleId,
            gameId = gameId,
            gameTitle = gameTitle,
            date = date,
            startTime = startTime,
            endTime = endTime,
            goalType = goalType,
            goalId = goalId,
            completeFlag = completeFlag,
            completeDay = completeDay,
            playTime = playTime,
            description = description
        )
}