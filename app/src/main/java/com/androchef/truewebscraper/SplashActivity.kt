package com.androchef.truewebscraper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.androchef.truewebscraper.feature.TrueWebScrapActivity
import com.androchef.truewebscraper.utils.executeWithDelay

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        executeWithDelay(3) {
            TrueWebScrapActivity.start(this)
            finish()
        }
    }
}