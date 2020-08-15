package com.androchef.truewebscraper.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * Adding disposables
     */
    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}