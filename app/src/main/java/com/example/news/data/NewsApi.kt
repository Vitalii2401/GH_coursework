package com.example.news.data

import com.example.news.data.api.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?/")
    fun getNewsForCountry(
        @Query("country") country: String
    ): Call<NewsResponse>

}