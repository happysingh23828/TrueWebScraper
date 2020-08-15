package com.androchef.contentscraper.scraper

import com.androchef.contentscraper.ContentScraper
import com.androchef.contentscraper.RemoteContentScraper
import com.androchef.remote.TrueWebScraperRepository
import io.reactivex.Single

internal class RemoteContentScraperImp constructor(
    private val trueWebScraperRepository: TrueWebScraperRepository,
    private val performActionOnRawContent: Boolean = false
) :
    RemoteContentScraper {

    private val localContentScraper = ContentScraper.get()

    override fun getNthCharacter(webUrl: String, n: Int): Single<String> {
        return getWebContent(webUrl)
            .flatMap {
                Single.just(localContentScraper.getNthCharacter(it, n))
            }
    }

    override fun getEveryNthCharacter(webUrl: String, n: Int): Single<String> {
        return getWebContent(webUrl)
            .flatMap {
                Single.just(localContentScraper.getEveryNthCharacter(it, n))
            }
    }

    override fun getUniqueWordsAndTheirCounts(webUrl: String): Single<String> {
        return getWebContent(webUrl)
            .flatMap {
                Single.just(localContentScraper.getUniqueWordsAndTheirCounts(it))
            }
    }

    private fun getWebContent(webUrl: String): Single<String> {
        return if (performActionOnRawContent)
            trueWebScraperRepository.getRawWebContent(webUrl)
        else
            trueWebScraperRepository.getPlainReadableContent(webUrl)
    }
}