package com.example.news.data.api

import com.example.news.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain) {
            val updateUrl = request().url().newBuilder()
                .addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()

            val updateRequest = request().newBuilder()
                .url(updateUrl)
                .build()
            proceed(updateRequest)
        }
    }
}