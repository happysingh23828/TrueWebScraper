package com.androchef.truewebscraper.feature

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.androchef.contentscraper.RemoteContentScraper
import com.androchef.truewebscraper.R
import com.androchef.truewebscraper.base.BaseActivity
import com.androchef.truewebscraper.feature.TrueWebScrapState.ContentType.*
import com.androchef.truewebscraper.utils.createFactory
import kotlinx.android.synthetic.main.activity_true_web_scrap.*

class TrueWebScrapActivity : BaseActivity() {

    companion object {
        private const val WEB_URL =
            "https://truecaller.blog/2018/01/22/life-as-an-android-engineer/"

        fun start(context: Context) {
            context.startActivity(Intent(context, TrueWebScrapActivity::class.java))
        }
    }

    private val remoteContentScraper = RemoteContentScraper.get()

    private lateinit var viewModel: TrueWebScrapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_true_web_scrap)
        init()
        onClicks()
        setObservers()
    }

    private fun init() {
        val factory = TrueWebScrapViewModel(WEB_URL, remoteContentScraper).createFactory()
        viewModel = ViewModelProviders.of(this, factory).get(TrueWebScrapViewModel::class.java)
    }

    private fun onClicks() {
        btnGetWebContents.setOnClickListener {
            fetchAllWebContent()
        }

        switchContent.setOnCheckedChangeListener { _, b ->
            viewModel.isToPerformActionsOnRawHTMLContent(b)
        }
    }

    private fun fetchAllWebContent() {
        viewModel.getNthCharacter(10)
        viewModel.getEveryNthCharacter(10)
        viewModel.getUniqueWordsAndTheirCounts()
    }

    private fun setObservers() {
        viewModel.stateObservable.subscribe {
            updateView(it)
        }.also { addDisposable(it) }
    }

    private fun updateView(trueWebScrapState: TrueWebScrapState) {
        when (trueWebScrapState) {
            is TrueWebScrapState.Loading -> showLoading(trueWebScrapState)
            is TrueWebScrapState.Error -> showError(trueWebScrapState)
            is TrueWebScrapState.Success -> showContent(trueWebScrapState)
        }
    }

    private fun showLoading(loading: TrueWebScrapState.Loading) {
        getTextViewForType(loading.contentType).text = getString(R.string.loading)
    }

    private fun showError(error: TrueWebScrapState.Error) {
        getTextViewForType(error.contentType).text =
            getString(R.string.error).plus(" ${error.error}")
    }


    private fun showContent(success: TrueWebScrapState.Success) {
        getTextViewForType(success.contentType).text = success.string
    }

    private fun getTextViewForType(contentType: TrueWebScrapState.ContentType): AppCompatTextView {
        return when (contentType) {
            NthChar -> tvNtnChar
            EveryNthChar -> tvEveryNtnChar
            UniqueWords -> tvUniqueWordsWithCount
        }
    }
}