package com.gameScheduleApp.models

data class ScheduleDisplayData(
    var displayCategory: Int,
    var date: String = "",
    var scheduleData: MutableList<ScheduleData>?
)