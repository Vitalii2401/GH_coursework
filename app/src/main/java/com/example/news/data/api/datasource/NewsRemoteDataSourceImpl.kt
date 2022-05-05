package com.example.news.data.api.datasource

import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel

class NewsRemoteDataSourceImpl : NewsDataSource.Remote {
    override suspend fun loadNews(): List<NewsDomainModel> {

    }
}