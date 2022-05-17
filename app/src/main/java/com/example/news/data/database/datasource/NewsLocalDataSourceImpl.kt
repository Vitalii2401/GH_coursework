package com.example.news.data.database.datasource

import com.example.news.data.database.dao.NewsDao
import com.example.news.data.database.mapper.mapEntityToNewsDomainModel
import com.example.news.data.database.mapper.mapNewsDomainModelToEntity
import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel

class NewsLocalDataSourceImpl(
    private val newsDao: NewsDao
) : NewsDataSource.Local {
    override suspend fun fetchNews(): List<NewsDomainModel> {
        return newsDao.fetchNews().map(::mapEntityToNewsDomainModel)
    }

    override suspend fun addNews(news: List<NewsDomainModel>) {
        newsDao.addNews(news.map(::mapNewsDomainModelToEntity))
    }

    override suspend fun deleteNews() {
        newsDao.deleteNews()
    }
}