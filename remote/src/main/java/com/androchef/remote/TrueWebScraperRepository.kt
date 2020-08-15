package com.androchef.remote

import com.androchef.remote.factory.RetrofitFactory
import com.androchef.remote.imp.TrueWebScraperRepositoryImp
import io.reactivex.Single

interface TrueWebScraperRepository {
    fun getRawWebContent(webUrl: String): Single<String>
    fun getPlainReadableContent(webUrl: String): Single<String>

    companion object {

        fun get(): TrueWebScraperRepository {
            return TrueWebScraperRepositoryImp(RetrofitFactory.create())
        }

    }
}