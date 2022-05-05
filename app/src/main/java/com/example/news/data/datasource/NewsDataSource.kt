package com.example.news.data.datasource

import com.example.news.domain.model.NewsDomainModel

interface NewsDataSource {

    interface Local {
        suspend fun fetchNews(): List<NewsDomainModel>
        suspend fun addNews(news: List<NewsDomainModel>)
    }

    interface Remote {
        suspend fun loadNews(): List<NewsDomainModel>
    }
}