package com.gameScheduleApp.models

data class GameListData(
    var gameId: Long = 0,
    var gameTitle: String = "",
    var lastPlayDate: String = "",
    var allPlayTime: String = ""
)