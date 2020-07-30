package com.gameScheduleApp.util.createTestData

import android.content.Context
import com.gameScheduleApp.models.GameListData
import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.db.game_list.GameListRepository
import com.gameScheduleApp.util.db.schedule.ScheduleRepository
import org.threeten.bp.LocalDate

class CreateTestData(mContext: Context) {

    val gameListRepository = GameListRepository(mContext)
    val scheduleRepository = ScheduleRepository(mContext)

    fun insertTestData() {

        // ゲーム一覧
//        gameListRepository.deleteAll()
//        var gameListDataList = createGameListData()
//        for (data in gameListDataList) {
//            gameListRepository.insert(data)
//        }

        // スケジュール
//        scheduleRepository.deleteAll(mContext)
//        var scheduleToday = createScheduleToday()
//        scheduleRepository.insertSchedule(mContext, scheduleToday)
        var updateScheduleDataToday = createUpdateTodayScheduleData()
        scheduleRepository.updateSchedule(updateScheduleDataToday)

    }

    fun createGameListData(): MutableList<GameListData> {
        var titleList = arrayListOf<String>(
            "The Ghost Of Tsushima",
            "The Last Of Us Part Ⅱ",
            "DOTA 2",
            "DARK SOULS Ⅲ",
            "Tom Clancy's Rainbow Six Siege",
            "DAEMON X MACHINA（デモンエクスマキナ）"
        )

        var gameListDataList = mutableListOf<GameListData>()
        for (title in titleList) {
            var gameListData: GameListData = GameListData(gameTitle = title)
            gameListDataList.add(gameListData)
        }

        return gameListDataList
    }

    fun createScheduleToday(): ScheduleData {
        var data = ScheduleData(
            scheduleId = 1L,
            gameId = 1L,
            gameTitle = "The Ghost Of Tsushima",
            date = LocalDate.now().toString(),
            startTime = "19:00",
            endTime = "22:00",
            goalType = "1",
            goalId = 1L,
            completeFlag = "0"
        )
        return data
    }

    private fun createUpdateTodayScheduleData(): ScheduleData {
        var scheduleData = scheduleRepository.selectScheduleById(1L)
        scheduleData.date = LocalDate.now().toString()
        return scheduleData
    }

//    fun createScheduleHistory(): MutableList<ScheduleData> {
//
//    }
}