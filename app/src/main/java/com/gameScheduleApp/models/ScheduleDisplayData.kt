package com.gameScheduleApp.models

data class ScheduleDisplayData(
    var yearMonth: String,
    var scheduleData: MutableList<ScheduleData>
)