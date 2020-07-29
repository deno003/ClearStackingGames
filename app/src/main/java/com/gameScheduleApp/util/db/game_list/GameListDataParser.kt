package com.gameScheduleApp.util.db.game_list

import com.gameScheduleApp.models.GameListData
import org.jetbrains.anko.db.MapRowParser

/*
 データベースで検索した結果の1行分をデータクラスにマッピングするクラス
 */

class GameListDataParser : MapRowParser<GameListData> {
    override fun parseRow(columns: Map<String, Any?>): GameListData {
        var row = GameListData(
            columns["game_id"] as Long,
            columns["game_title"] as String,
            columns["last_play_date"] as String,
            columns["all_play_time"] as String
        )
        return row
    }
}

