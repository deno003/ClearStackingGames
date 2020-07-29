package com.gameScheduleApp.models

data class ScheduleData(
    var scheduleId: Long = 0,
    var gameId: Long = 0,
    var gameTitle: String = "",
    var date: String = "",
    var startTime: String? = null,
    var endTime: String? = null,
    var goalType: String = "",
    var goalId: Long = 0,
    var completeFlag: String = "",
    var completeDay: String? = null,
    var playTime: String? = null,
    var description: String? = null
)