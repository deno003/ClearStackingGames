package com.gameScheduleApp.util

import android.content.Context
import android.content.SharedPreferences

// PresenterにcontextやSharedPreferencesをそのまま渡すとViewとPresenterの責務が曖昧になる
// →SharedPreferencesをラップして渡す
class SharedPreferencesService(private val mContext: Context) {
    val pref: SharedPreferences by lazy {
        mContext.getSharedPreferences("pref1", Context.MODE_PRIVATE)
    }

    fun applay(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }
}
