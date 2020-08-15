package com.androchef.truewebscraper.utils

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
    val viewModel = this
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
    }
}

fun AppCompatActivity.executeWithDelay(delayInSeconds: Int, start: () -> Unit) {
    Handler().postDelayed({
        start.invoke()
    }, delayInSeconds.times(1000L))
}