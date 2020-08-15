package com.androchef.contentscraper

import com.androchef.contentscraper.scraper.RemoteContentScraperImp
import com.androchef.remote.TrueWebScraperRepository
import io.reactivex.Single

interface RemoteContentScraper {
    fun getNthCharacter(webUrl: String, n: Int): Single<String>
    fun getEveryNthCharacter(webUrl: String, n: Int): Single<String>
    fun getUniqueWordsAndTheirCounts(webUrl: String): Single<String>

    companion object {
        fun get(): RemoteContentScraper {
            return RemoteContentScraperImp(TrueWebScraperRepository.get())
        }
    }
}