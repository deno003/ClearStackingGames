package com.gameScheduleApp.util


import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DateFormatter {

    fun formatPattern1(mDate: String): String {
        val localDate: LocalDate = LocalDate.parse(mDate)

        val date: String = localDate.monthValue.toString() + "月" + localDate.dayOfMonth + "日"

        return date
    }

    fun formatPattern2(mDate: String): String {
        val localDate: LocalDate = LocalDate.parse(mDate)
        val formatDate: String = localDate.format(DateTimeFormatter.ofPattern("YYYY年MM月dd日(EEE)"))
        return formatDate.toString()
    }

    fun todayPattern2(): String {
        val formatDate: String =
            LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY年MM月dd日(EEE)"))
        return formatDate.toString()
    }

    // 曜日取得
    fun dayOfWeek(mDate: String): String {
        return LocalDate.parse(mDate).format(DateTimeFormatter.ofPattern("EEE"))
    }

    // 日付取得
    fun day(mDate: String): String {
        var a = LocalDate.parse(mDate)
        a.format(DateTimeFormatter.ofPattern("dd"))
        return LocalDate.parse(mDate).format(DateTimeFormatter.ofPattern("dd"))
    }

    // 年月取得
    fun yearMonth(mDate: String): String {
        return LocalDate.parse(mDate).format(DateTimeFormatter.ofPattern("YYYY年MM月"))
    }
}