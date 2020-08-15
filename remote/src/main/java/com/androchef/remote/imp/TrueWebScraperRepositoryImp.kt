package com.androchef.remote.imp

import com.androchef.remote.TrueWebScraperRepository
import com.androchef.remote.service.TrueWebScraperAPIService
import com.androchef.remote.utils.takeOnlyValidCharacters
import com.androchef.remote.utils.transform
import io.reactivex.Single
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

internal class TrueWebScraperRepositoryImp constructor(private val trueWebScraperAPIService: TrueWebScraperAPIService) :
    TrueWebScraperRepository {

    override fun getRawWebContent(webUrl: String): Single<String> {
        return Single.create { emitter ->
            val rawService = trueWebScraperAPIService.getWebRawContent(webUrl)
            rawService.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emitter.onSuccess(it)
                        } ?: kotlin.run {
                            emitter.onError(Exception("No valid result found."))
                        }
                    } else {
                        emitter.onError(Exception("Something went wrong, please try again."))
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    emitter.onError(t.transform())
                }
            })
        }
    }

    override fun getPlainReadableContent(webUrl: String): Single<String> {
        return Single.create { emitter ->
            val rawService = trueWebScraperAPIService.getWebRawContent(webUrl)
            rawService.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        response.body()?.let {

                            // Converting raw content to plain readable format using jsoup.
                            val convertedPlainTxt = Jsoup.parse(it).text()
                            emitter.onSuccess(convertedPlainTxt.takeOnlyValidCharacters())

                        } ?: kotlin.run {
                            emitter.onError(Exception("No valid result found."))
                        }
                    } else {
                        emitter.onError(Exception("Something went wrong, please try again."))
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    emitter.onError(t.transform())
                }
            })
        }
    }
}