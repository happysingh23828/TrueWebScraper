package com.androchef.truewebscraper.utils

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment

fun View.visible(): View {
    this.visibility = View.VISIBLE
    if (this is Group) {
        this.requestLayout()
    }
    return this
}

fun View.inVisible(): View {
    this.visibility = View.INVISIBLE
    if (this is Group) {
        this.requestLayout()
    }
    return this
}

fun View.gone(): View {
    this.visibility = View.GONE
    if (this is Group) {
        this.requestLayout()
    }
    return this
}

fun AppCompatActivity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
