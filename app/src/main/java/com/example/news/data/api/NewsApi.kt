package com.example.news.data.api

import com.example.news.data.api.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?/")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
    ): Response<NewsResponse>

}