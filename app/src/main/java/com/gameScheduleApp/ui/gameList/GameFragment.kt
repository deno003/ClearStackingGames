package com.gameScheduleApp.ui.gameList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.gameScheduleApp.models.GameListData
import com.gameScheduleApp.util.db.game_list.GameListRepository

/**
 *
 */
class GameFragment : Fragment(), GameListContract.View {

    // Fragmentに属する各パーツを宣言する→Fragment内の各処理で使用できるように
    private lateinit var gsf3001GameListRecyclerView: RecyclerView

    override lateinit var presenter: GameListContract.Presenter

    override var isActive: Boolean = false
        get() = isAdded

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.gs3001_game_list, container, false)

        // 宣言した各パーツ変数と、画面パーツの結びつけ
        with(view) {
            gsf3001GameListRecyclerView = findViewById(R.id.gs2001_schedule_recycler_view)
        }

        // prensenterの生成
        presenter = GameListPresenter(this, GameListRepository(view.context))

        return view
    }

    override fun showGameList(displayData: MutableList<GameListData>) {
        // アダプターとレイアウトマネージャーの設定
        with(gsf3001GameListRecyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = GameListAdapter(displayData)
        }
    }


    override fun sort1() {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = GameFragment()
    }
}