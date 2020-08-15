package com.androchef.remote.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

internal interface TrueWebScraperAPIService {

    @GET
    fun getWebRawContent(@Url webUrl: String) : Call<String>
}