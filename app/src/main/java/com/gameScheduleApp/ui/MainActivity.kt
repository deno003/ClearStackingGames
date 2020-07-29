package com.gameScheduleApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.gameScheduleApp.ui.gameList.GameListPresenter

class MainActivity : AppCompatActivity() {

    private lateinit var gameListPresenter: GameListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gs0000_main_activity)
    }
}