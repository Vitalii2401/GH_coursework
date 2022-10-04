package com.example.news.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.google.firebase.auth.FirebaseUser

interface NewsRepository {
    suspend fun loadNews()
    suspend fun fetchNewsFromDatabase(): List<NewsDomainModel>

    fun getListBookmarks(): LiveData<List<BookmarksModel>>
    fun getCountBookmarks(): LiveData<Long>
    fun addNewsToBookmarks(news: NewsDomainModel, resultLiveData: MutableLiveData<String>)
    fun deleteNewsFromBookmarks(id: String, resultLiveData: MutableLiveData<String>)

    fun setFirebaseUser(user: FirebaseUser?)
    fun getFirebaseUser(): MutableLiveData<FirebaseUser?>
    fun logOut()

    fun removeBookmarksListener()
}