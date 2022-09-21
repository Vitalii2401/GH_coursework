package com.example.news.domain.repository

import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel

interface NewsRepository {
    suspend fun fetchNews(): List<NewsDomainModel>
    suspend fun loadNews()

    suspend fun addNewsToBookmarks(news: NewsDomainModel): String
    suspend fun deleteNewsFromBookmarks(id: String)
    suspend fun getListBookmarks(): List<BookmarksModel>
}