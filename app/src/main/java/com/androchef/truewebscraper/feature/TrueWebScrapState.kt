package com.androchef.truewebscraper.feature

sealed class TrueWebScrapState {

    object Init : TrueWebScrapState()

    data class Loading(
        val contentType: ContentType
    ) : TrueWebScrapState()

    data class Error(
        val error: String,
        val contentType: ContentType
    ) : TrueWebScrapState()

    data class Success(
        val string: String,
        val contentType: ContentType
    ) : TrueWebScrapState()

    enum class ContentType {
        NthChar, EveryNthChar, UniqueWords
    }
}