package com.example.news.data.datasource

import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel

interface NewsDataSource {

    interface Local {
        suspend fun fetchNews(): List<NewsDomainModel>
        suspend fun addNews(news: List<NewsDomainModel>)
        suspend fun deleteNews()
    }

    interface Remote {
        suspend fun loadNews(): List<NewsDomainModel>
    }

    interface Firebase {
        suspend fun saveNews(news: NewsDomainModel): String
        suspend fun deleteNews(id: String)
        suspend fun getListNews(): List<BookmarksModel>
    }
}