package com.gameScheduleApp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class TitleActivity : AppCompatActivity() {

    private val handler = Handler();
    private val runnable = Runnable {

        // MainActivityへ遷移
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        // finish()で終了させないとAndroidの◁で戻れてしまう
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gs0001_title)

        // スプラッシュ表示後にrunnableをcall
        handler.postDelayed(runnable, 1000)
    }

    // コールバックを取り消す→これが無いとアプリを落とした後にまた表示されてしまう
    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}

