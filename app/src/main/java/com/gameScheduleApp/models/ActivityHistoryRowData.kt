package com.gameScheduleApp.models

/**
 * アクティビティヒストリー1件分のデータ
 */

data class ActivityHistoryRowData(
    var activityContent: String = "",
    var playTime: String = "",
    var playTimeInAWeek: String = "",
    var totalPlayTime: String = "",
    var scheduleId: Long = 0
)