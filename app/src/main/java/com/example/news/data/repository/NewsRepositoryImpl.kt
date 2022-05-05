package com.example.news.data.repository

import com.example.news.data.api.datasource.NewsRemoteDataSourceImpl
import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel
import com.example.news.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsDataSource.Remote
) : NewsRepository {

    override suspend fun fetchNews(): List<NewsDomainModel> {

    }

    override suspend fun loadNews(): List<NewsDomainModel> {
        newsRemoteDataSource.loadNews()
    }
}