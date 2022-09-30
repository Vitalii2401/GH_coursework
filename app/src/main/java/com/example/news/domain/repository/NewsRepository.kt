package com.example.news.domain.repository

import androidx.lifecycle.LiveData
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.google.firebase.auth.FirebaseUser

interface NewsRepository {
    suspend fun fetchNews(): List<NewsDomainModel>
    suspend fun loadNews()

    suspend fun addNewsToBookmarks(news: NewsDomainModel): String
    suspend fun deleteNewsFromBookmarks(id: String)
    suspend fun getFirebaseUser(): FirebaseUser?
    suspend fun setFirebaseUser(user: FirebaseUser?)

    fun getListBookmarks(): LiveData<List<BookmarksModel>>
    fun removeBookmarksListener()
}