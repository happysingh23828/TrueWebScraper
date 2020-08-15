package com.androchef.remote

import io.reactivex.Single

interface TrueWebScraperRepository {
    fun getRawWebContent(webUrl: String): Single<String>
    fun getPlainReadableContent(webUrl: String): Single<String>
}