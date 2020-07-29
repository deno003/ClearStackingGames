package com.gameScheduleApp.ui.home

import com.gameScheduleApp.models.ActivityHistoryBaseRowData
import com.gameScheduleApp.models.ActivityHistoryRowData
import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.db.game_list.GameListRepository
import com.gameScheduleApp.util.db.schedule.ScheduleRepository

class HomePresenter(
    private val homeView: HomeContract.View,
    private val scheduleRepository: ScheduleRepository,
    private val gameListRepository: GameListRepository
) : HomeContract.Presenter {

    init {
        homeView.presenter = this
    }

    override fun start() {
        openTodaySchedule()
        openActivity()

    }

    override fun openTodaySchedule() {
        val todaySchedule = scheduleRepository.selectTodaySchedule()[0]
        with(homeView) {
            showTodaySchedule(todaySchedule)
        }
    }

    override fun openActivity() {
        // scheduleデータ取得（→数が増えた時に重くなるので最大数の設定は後々必要）
        val historyData: MutableList<ScheduleData> = scheduleRepository.selectScheduleHistory()
        var activityList: MutableList<ActivityHistoryBaseRowData> = mutableListOf()
        for (history in historyData) {
            // 日付ごとにActivityHistoryBaseRowDataを作成
            // 同じ日付のActivityHistoryBaseRowDataが無かった場合
            if (!activityList.any { rowData -> rowData.date == history.completeDay }) {
                var historyRowData = ActivityHistoryRowData(
                    activityContent = createActivityContent(history),
                    playTimeInAWeek = sumPlayTimeInAWeek(history.gameId),
                    totalPlayTime = gameListRepository.selectById(history.gameId).allPlayTime
                )
                var historyBaseRowData = ActivityHistoryBaseRowData(
                    date = history.completeDay,
                    activityHistoryContentList = mutableListOf<ActivityHistoryRowData>(
                        historyRowData
                    )
                )
                activityList.add(historyBaseRowData)
            } else {// 同じ日付のActivityHistoryBaseRowDataがあった場合
                var historyRowData = ActivityHistoryRowData(
                    activityContent = createActivityContent(history),
                    playTimeInAWeek = sumPlayTimeInAWeek(history.gameId),
                    totalPlayTime = gameListRepository.selectById(history.gameId).allPlayTime
                )
                var historyBaseRowData: ActivityHistoryBaseRowData? =
                    activityList.find { rowData -> rowData.date == history.completeDay }
                if (historyBaseRowData != null) {
                    historyBaseRowData.activityHistoryContentList.add(historyRowData)
                    activityList.add(historyBaseRowData)
                }
            }
        }

        // 重複削除・降順ソート
        var activityList2: MutableList<ActivityHistoryBaseRowData> =
            activityList.distinctBy { it.date }.sortedByDescending { it.date }.toMutableList()
        with(homeView) {
            showActivity(activityList2)
        }
    }

    // Activity文言生成（
    // →"XXXXを初めてプレイしました。"とか、"N個の実績を解除しました"とか、scheduleを設定した時のgoalTypeによって変更する予定
    private fun createActivityContent(scheduleData: ScheduleData): String {
        // XXXXをZZ時間プレイしました。
        var activityContent: String =
            scheduleData.gameTitle + "を" + scheduleData.playTime + "時間プレイしました。"

        return activityContent
    }

    // 過去1週間のプレイ時間取得
    private fun sumPlayTimeInAWeek(gameId: Long): String {
        // 過去1週間の履歴取得
        var historyInAWeek: MutableList<ScheduleData> = scheduleRepository.selectScheduleInAWeek()
        // gameIdと合致する履歴のみ抽出
        var historyInAWeekOnlyAGame: MutableList<ScheduleData> =
            historyInAWeek.filter { h -> h.gameId == gameId }.toMutableList()
        // 各scheduleのプレイ時間を合計
        var playTimeInAWeek: String =
            historyInAWeekOnlyAGame.sumBy { it.playTime!!.toInt() ?: 0 }.toString()

        return playTimeInAWeek
    }


}