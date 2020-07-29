package com.gameScheduleApp.util.db.game_list

import android.content.Context
import com.gameScheduleApp.models.GameListData
import org.jetbrains.anko.db.dropTable
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.threeten.bp.LocalDate

class GameListRepository(mContext: Context) {

    private val mContext: Context = mContext

    /*
    gameList:全量検索
     */
    fun selectAll(): MutableList<GameListData> {
        val database = GameListDatabaseOpenHelper.getInstance(mContext).readableDatabase

        val selectResult =
            database.select("game_list").parseList<GameListData>(GameListDataParser())

        return selectResult.toMutableList()
    }

    /*
    gameList:ID検索
     */
    fun selectById(gameId: Long): GameListData {
        val database = GameListDatabaseOpenHelper.getInstance(mContext).readableDatabase
        val selectResult =
            database.select("game_list").whereArgs("game_id = {gameId}", "gameId" to gameId)
                .parseSingle(GameListDataParser())
        return selectResult
    }

    /*
    gameList:登録
     */
    fun insert(insertData: GameListData) {
        val database = GameListDatabaseOpenHelper.getInstance(mContext).writableDatabase
        database.insert(
            "game_list",
            "game_title" to insertData.gameTitle,
            "last_play_date" to LocalDate.now().toString(),
            "all_play_time" to "0"
        )
    }

    /*
    gameList:更新
     */

    /*
    テーブル削除
     */
    fun dropTable() {
        val database = GameListDatabaseOpenHelper.getInstance(mContext).writableDatabase
        database.dropTable("game_list", true)
    }

    /*
    game_list 削除（全て）
     */
    fun deleteAll() {
        val database = GameListDatabaseOpenHelper.getInstance(mContext).writableDatabase
        database.delete("game_list", null, null)
    }

}