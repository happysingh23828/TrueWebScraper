package com.androchef.truewebscraper.feature

import androidx.lifecycle.MutableLiveData
import com.androchef.contentscraper.RemoteContentScraper
import com.androchef.truewebscraper.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TrueWebScrapViewModel(
    private val webURLToScrap: String,
    private val remoteContentScraper: RemoteContentScraper
) :
    BaseViewModel<TrueWebScrapState>() {

    private var state: TrueWebScrapState = TrueWebScrapState.Init
        set(value) {
            field = value
            publishState(value)
        }

    fun isToPerformActionsOnRawHTMLContent(boolean: Boolean) {
        remoteContentScraper.performActionOnRawHtmlContent(boolean)
    }

    fun getNthCharacter(n: Int) {
        state = TrueWebScrapState.Loading(TrueWebScrapState.ContentType.NthChar)
        remoteContentScraper.getNthCharacter(webURLToScrap, n)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state = TrueWebScrapState.Success(
                    "${n}th character in response is $it",
                    TrueWebScrapState.ContentType.NthChar
                )
            }, {
                state = TrueWebScrapState.Error(
                    it.localizedMessage,
                    TrueWebScrapState.ContentType.NthChar
                )
            })
            .also { addDisposable(it) }
    }

    fun getEveryNthCharacter(n: Int) {
        state = TrueWebScrapState.Loading(TrueWebScrapState.ContentType.EveryNthChar)
        remoteContentScraper.getEveryNthCharacter(webURLToScrap, n)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state = TrueWebScrapState.Success(
                    "Every ${n}th character in response\n\n[ $it ]",
                    TrueWebScrapState.ContentType.EveryNthChar
                )
            }, {
                state = TrueWebScrapState.Error(
                    it.localizedMessage,
                    TrueWebScrapState.ContentType.EveryNthChar
                )
            })
            .also { addDisposable(it) }
    }

    fun getUniqueWordsAndTheirCounts() {
        state = TrueWebScrapState.Loading(TrueWebScrapState.ContentType.UniqueWords)
        remoteContentScraper.getUniqueWordsAndTheirCounts(webURLToScrap)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                state = TrueWebScrapState.Success(
                    "Unique words and their counts in response\n\n$it",
                    TrueWebScrapState.ContentType.UniqueWords
                )
            }, {
                state = TrueWebScrapState.Error(
                    it.localizedMessage,
                    TrueWebScrapState.ContentType.UniqueWords
                )
            })
            .also { addDisposable(it) }
    }


    override val stateObservable: Observable<TrueWebScrapState> by lazy {
        Observable.create<TrueWebScrapState> {
            stateEmitter = it
        }
    }
}