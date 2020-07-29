package com.gameScheduleApp.util

import org.threeten.bp.Duration
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter


class TimeFormatter {

    // 文字列のtimeをdb登録用に変換
    fun formatForRegistration(mTime: String?): String {
        return LocalTime.parse(mTime).toString()
    }

    fun formatPattern1(mTime: String?): String {
        return LocalTime.parse(mTime).format(DateTimeFormatter.ofPattern("HH:SS")).toString()
    }

    // 時間取得
    fun duration(from: String?, to: String?): String {
        var fromTime: LocalTime = LocalTime.parse(from)
        var toTime: LocalTime = LocalTime.parse(to)

        return Duration.between(fromTime, toTime).toHours().toString()
    }

    // 期間表示
    fun formatPeriod(from: String?, to: String?): String {
        var fromTime: String = formatPattern1(from)
        var toTime: String = formatPattern1(to)

        return "$fromTime - $toTime"
    }
}