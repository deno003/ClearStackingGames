package com.gameScheduleApp.util.db.schedule

import com.gameScheduleApp.models.ScheduleData
import org.jetbrains.anko.db.MapRowParser

class ScheduleDataParser : MapRowParser<ScheduleData> {
    override fun parseRow(columns: Map<String, Any?>): ScheduleData {
        var row = ScheduleData(
            columns["schedule_id"] as Long,
            columns["game_id"] as Long,
            columns["game_title"] as String,
            columns["date"] as String,
            columns["start_time"] as String?,
            columns["end_time"] as String?,
            columns["goal_type"] as String,
            columns["goal_id"] as Long,
            columns["complete_flag"] as String,
            columns["complete_day"] as String?,
            columns["play_time"] as String?,
            columns["description"] as String?
        )
        return row
    }
}