package com.example.news.data.api

import com.example.news.data.api.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?/")
    fun getNewsForCountry(
        @Query("country") country: String
    ): Response<NewsResponse>

}