package com.gameScheduleApp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.gameScheduleApp.ui.gameList.GameListPresenter
import com.gameScheduleApp.util.createTestData.CreateTestData
import com.jakewharton.threetenabp.AndroidThreeTen

class MainActivity : AppCompatActivity() {

    private lateinit var gameListPresenter: GameListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gs0000_main_activity)
        AndroidThreeTen.init(this);

        // テスト表示用DB調整
        CreateTestData(this).insertTestData()
    }
}