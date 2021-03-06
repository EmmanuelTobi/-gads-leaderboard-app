package com.cosmic.gadsleaderboard.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cosmic.gadsleaderboard.R

class SplashActivity : AppCompatActivity() {

    internal var handler = Handler()
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        this.context = this@SplashActivity

        handler.postDelayed(postTask,
            delayTime
        )
    }

    override fun onDestroy() {
        handler.removeCallbacks(postTask)
        super.onDestroy()
    }

    internal var postTask: Runnable = Runnable {

        startActivity(Intent(context, MainActivity::class.java))
        overridePendingTransition(0, 0)
        finish()
    }

    companion object {

        private val delayTime: Long = 1000
    }
}