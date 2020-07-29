package com.gameScheduleApp.models

/**
 * アクティビティヒストリーベース1件分のデータ
 */

data class ActivityHistoryBaseRowData(
    var date: String? = "",
    var activityHistoryContentList: MutableList<ActivityHistoryRowData> = mutableListOf<ActivityHistoryRowData>()
)