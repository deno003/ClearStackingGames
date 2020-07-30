package com.gameScheduleApp.models

data class ScheduleDisplayData2(
    var displayCategory: Int,
    var date: String = "",
    var scheduleData: MutableList<ScheduleData>?
)

data class ScheduleDisplayData3(
    var displayCategory: Int,
    var scheduleData: MutableList<ScheduleData>
)