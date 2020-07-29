package com.gameScheduleApp.ui.gameList

import com.gameScheduleApp.util.db.game_list.GameListRepository

class GameListPresenter(
    private val gameListView: GameListContract.View,
    private val gameListRepository: GameListRepository
//    ,
//    private val pref: SharedPreferences
) : GameListContract.Presenter {

    init {
        gameListView.presenter = this
    }

    override fun start() {
        openGameList()
    }

    override fun openGameList() {
        val gameList = gameListRepository.selectAll()
        with(gameListView) {
            showGameList(gameList)
        }
        return
    }

}