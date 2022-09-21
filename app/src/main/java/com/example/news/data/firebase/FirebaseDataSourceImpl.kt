package com.example.news.data.firebase

import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel

class FirebaseDataSourceImpl : NewsDataSource.Firebase {

    override suspend fun saveNews(news: NewsDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(news: NewsDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getListNews(): List<NewsDomainModel> {
        TODO("Not yet implemented")
    }
}