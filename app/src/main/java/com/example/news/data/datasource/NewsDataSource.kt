package com.example.news.data.datasource

import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.google.firebase.auth.FirebaseUser

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
        suspend fun getUser(): FirebaseUser
        suspend fun setUser(user: FirebaseUser)
    }
}