package com.androchef.remote.factory

import com.androchef.remote.service.TrueWebScraperAPIService
import okhttp3.Dns
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    //not considering base Url , as it will take URL parameter while creating apiService instance.
    private const val DEFAULT_BASE_URL = "http://androchef.com/"

    fun create(): TrueWebScraperAPIService {
        val client = createOkHttp()

        val retrofit = Retrofit.Builder()
            .baseUrl(DEFAULT_BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        return retrofit.create(TrueWebScraperAPIService::class.java)
    }

    private fun createOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .dns(Dns.SYSTEM)
            .cache(null)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private const val OK_HTTP_TIMEOUT = 60L
}