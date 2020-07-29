package com.gameScheduleApp.ui.gameList

import com.gameScheduleApp.models.GameListData
import com.gameScheduleApp.util.BasePresenter
import com.gameScheduleApp.util.BaseView

class GameListContract {
    interface View : BaseView<Presenter> {
        val isActive: Boolean

        fun showGameList(displayData: MutableList<GameListData>)

        fun sort1()
    }

    interface Presenter : BasePresenter {
        fun openGameList()
    }
}