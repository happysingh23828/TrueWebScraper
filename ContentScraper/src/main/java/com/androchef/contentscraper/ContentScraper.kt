package com.androchef.contentscraper

import com.androchef.contentscraper.scraper.ContentScraperImp

interface ContentScraper {
    fun getNthCharacter(inputString: String, n: Int): String?
    fun getEveryNthCharacter(inputString: String, n: Int): String?
    fun getUniqueWordsAndTheirCounts(inputString: String): String?

    companion object {

        private val contentScraper = ContentScraperImp()

        fun get(): ContentScraper {
            return contentScraper
        }

    }
}