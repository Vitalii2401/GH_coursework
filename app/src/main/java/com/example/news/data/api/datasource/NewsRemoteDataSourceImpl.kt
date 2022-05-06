package com.example.news.data.api.datasource

import com.example.news.data.api.NewsApi
import com.example.news.data.api.mapper.mapNewsToDomain
import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel

class NewsRemoteDataSourceImpl(
    private val newsApi: NewsApi
) : NewsDataSource.Remote {
    override suspend fun loadNews(): List<NewsDomainModel> {
        return newsApi.getNewsForCountry("ua").body()?.articles?.map(::mapNewsToDomain)
            ?: emptyList()
    }
}