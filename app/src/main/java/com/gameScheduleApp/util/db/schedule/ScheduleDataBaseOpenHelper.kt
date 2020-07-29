package com.gameScheduleApp.util.db.schedule

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class ScheduleDataBaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "schedule_db.db", null, 1) {

    companion object {
        val tableName = "schedule"
        private var instance: ScheduleDataBaseOpenHelper? = null

        fun getInstance(mContext: Context): ScheduleDataBaseOpenHelper {
            return instance ?: ScheduleDataBaseOpenHelper(mContext.applicationContext)
        }
    }

    // テーブル作成
    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            createTable(
                tableName, ifNotExists = true,
                columns = *arrayOf(
                    "schedule_id" to INTEGER + PRIMARY_KEY,
                    "game_id" to INTEGER,
                    "game_title" to TEXT + NOT_NULL,
                    "date" to TEXT + NOT_NULL,
                    "start_time" to TEXT,
                    "end_time" to TEXT,
                    "goal_type" to TEXT,
                    "goal_id" to INTEGER,
                    "complete_flag" to TEXT,
                    "complete_day" to TEXT,
                    "play_time" to TEXT,
                    "description" to TEXT
                )
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("schedule", true)
    }
}