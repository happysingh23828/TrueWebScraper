package com.androchef.truewebscraper.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<T> : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    protected var stateEmitter: ObservableEmitter<T>? = null

    abstract val stateObservable: Observable<T>

    protected open fun publishState(state: T){
        if (stateEmitter != null) {
            stateEmitter!!.onNext(state)
        }
    }

    protected fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    private fun clearDisposables() {
        if (disposables.isDisposed.not())
            disposables.clear()
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        clearDisposables()
    }
}
