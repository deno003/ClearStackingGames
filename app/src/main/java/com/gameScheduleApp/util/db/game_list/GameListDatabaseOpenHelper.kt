package com.gameScheduleApp.util.db.game_list

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class GameListDatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "gameListDb.db", null, 1) {

    // objectのキャッシュのためCompanion Object使用（Javaで言うところのstatic)
    companion object {
        val tableName = "game_list"
        private var instance: GameListDatabaseOpenHelper? = null

        // ?: はエルビス演算子　→　nullで無ければオブジェクトを返して、nullの場合は生成して返す
        fun getInstance(context: Context): GameListDatabaseOpenHelper {
            return instance
                ?: GameListDatabaseOpenHelper(
                    context.applicationContext
                )
        }
    }

    // テーブルの作成
    // run拡張関数を使用　→　nullでなければテーブル作成
    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            createTable(
                tableName, ifNotExists = true,
                columns = *arrayOf(
                    "game_id" to INTEGER + PRIMARY_KEY,
                    "game_title" to TEXT,
                    "last_play_date" to TEXT,
                    "all_play_time" to TEXT
                )
            )
        }
    }

    // データベースのバージョンアップの際に使用されるメソッド→とりあえず今回は空実装
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.dropTable("game_list", true)
    }

    // Access property for Context
    // コンテキストにアクセスできる場所なら、何処からでもdbにアクセス可能
    val Context.database: GameListDatabaseOpenHelper
        get() = GameListDatabaseOpenHelper.getInstance(this)
}