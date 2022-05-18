package com.example.news.data.api.datasource

import android.content.Context
import android.widget.Toast
import com.example.news.data.api.NewsApi
import com.example.news.data.api.mapper.mapNewsToDomain
import com.example.news.data.datasource.NewsDataSource
import com.example.news.data.objects.RequestParam
import com.example.news.domain.model.NewsDomainModel

class NewsRemoteDataSourceImpl(
    private val newsApi: NewsApi,
    private val context: Context
) : NewsDataSource.Remote {
    override suspend fun loadNews(): List<NewsDomainModel> {
        return newsApi.getNews(RequestParam.COUNTRY, RequestParam.CATEGORY).let {
            if(it.isSuccessful)
                it.body()?.articles?.map(::mapNewsToDomain) ?: emptyList()
            else {
                Toast.makeText(context, "Error ${it.code()}", Toast.LENGTH_SHORT).show()
                emptyList()
            }
        }
    }
}