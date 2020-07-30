package com.gameScheduleApp.util.db.schedule

import android.content.Context
import com.gameScheduleApp.models.ScheduleData
import com.gameScheduleApp.util.TimeFormatter
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import org.threeten.bp.LocalDate

class ScheduleRepository(mContext: Context) {
    private val mContext: Context = mContext

    /*
    schedule: 今日の予定検索
     */
    fun selectTodaySchedule(): MutableList<ScheduleData> {
        val today: String = LocalDate.now().toString()
        val database = ScheduleDataBaseOpenHelper.getInstance(mContext).readableDatabase
        val selectResult =
            database.select("schedule")
                .whereArgs("date like {today}", "today" to today)
                .parseList(ScheduleDataParser())

        return selectResult.toMutableList()
    }

    /*
    schedule: ID検索
     */
    fun selectScheduleById(scheduleId: Long): ScheduleData {
        val database = ScheduleDataBaseOpenHelper.getInstance(mContext).readableDatabase
        val selectResult =
            database.select("schedule")
                .whereArgs("schedule_id = {scheduleId}", "scheduleId" to scheduleId)
                .parseSingle(ScheduleDataParser())

        return selectResult
    }

    /*
    schedule: 過去
     */
    fun selectScheduleHistory(): MutableList<ScheduleData> {
        val database = ScheduleDataBaseOpenHelper.getInstance(mContext).readableDatabase
        val selectResult =
            database.select("schedule").whereArgs("complete_flag = 1").orderBy("complete_day")
                .parseList(ScheduleDataParser())

        return selectResult.toMutableList()
    }

    /*
    schedule: 過去1週間分検索   
     */
    fun selectScheduleInAWeek(): MutableList<ScheduleData> {
        val database = ScheduleDataBaseOpenHelper.getInstance(mContext).readableDatabase
        // 1週間前の情報取得
        // TODO テストデータ表示用に日時指定　本来は上のコードを使用
//        val oneWeekAgo = LocalDate.now().minusWeeks(1L).toString()
        val oneWeekAgo = LocalDate.of(2020, 7, 20).minusWeeks(1L).toString()
        val selectResult =
            database.select("schedule").whereArgs(
                "complete_flag = 1 AND complete_day > {oneWeekAgo}",
                "oneWeekAgo" to oneWeekAgo
            ).orderBy("complete_day")
                .parseList(ScheduleDataParser())

        return selectResult.toMutableList()
    }

    /*
    schedule: 全量検索
     */
    fun selectAll(): MutableList<ScheduleData> {
        val database = ScheduleDataBaseOpenHelper.getInstance(mContext).readableDatabase
        val selectResult =
            database.select("schedule").parseList(ScheduleDataParser())

        return selectResult.toMutableList()
    }

    /*
    schedule: 登録
     */
    fun insertSchedule(insertData: ScheduleData) {
        val database = ScheduleDataBaseOpenHelper.getInstance(mContext).writableDatabase
        database.insert(
            "schedule",
            "game_id" to insertData.gameId,
            "game_title" to insertData.gameTitle,
            "date" to insertData.date,
            "start_time" to TimeFormatter().formatForRegistration(insertData.startTime),
            "end_time" to TimeFormatter().formatForRegistration(insertData.endTime),
            "goal_type" to insertData.goalType,
            "goal_id" to insertData.goalId,
            "complete_flag" to insertData.completeFlag,
            "complete_day" to "",
            "play_time" to ""
        )
    }

    /*
    schedule:更新
     */
    fun updateSchedule(updateData: ScheduleData) {
        val database = ScheduleDataBaseOpenHelper.getInstance(mContext).writableDatabase
        // 更新対象存在チェック
        val target = database.select("schedule")
            .whereArgs("schedule_id = {scheduleId}", "scheduleId" to updateData.scheduleId)

        database.update(
            "schedule",
            "game_id" to updateData.gameId,
            "game_title" to updateData.gameTitle,
            "date" to updateData.date,
            "start_time" to TimeFormatter().formatForRegistration(updateData.startTime),
            "end_time" to TimeFormatter().formatForRegistration(updateData.endTime),
            "goal_type" to updateData.goalType,
            "goal_id" to updateData.goalId,
            "complete_flag" to updateData.completeFlag,
            "play_time" to updateData.playTime,
            "description" to updateData.description
        ).whereArgs("schedule_id = {scheduleId}", "scheduleId" to updateData.scheduleId).exec()
    }

    /*
    schedule:全削除
     */
    fun deleteAll() {
        val database = ScheduleDataBaseOpenHelper.getInstance(mContext).writableDatabase
        database.delete("schedule", null, null)
    }

}