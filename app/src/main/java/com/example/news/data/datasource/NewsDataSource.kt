package com.example.news.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.google.firebase.auth.FirebaseUser

interface NewsDataSource {

    interface Local {
        suspend fun fetchNewsFromDatabase(): List<NewsDomainModel>
        suspend fun addNewsToDatabase(news: List<NewsDomainModel>)
        suspend fun clearDatabase()
    }

    interface Remote {
        suspend fun loadNews(): List<NewsDomainModel>
    }

    interface Firebase {
        fun getListBookmarks(): LiveData<List<BookmarksModel>>
        fun getCountBookmarks(): LiveData<Long>
        fun addNewsToBookmarks(news: NewsDomainModel, resultLiveData: MutableLiveData<String>)
        fun deleteNewsFromBookmarks(id: String, resultLiveData: MutableLiveData<String>)

        fun setUser(user: FirebaseUser?)
        fun getUser(): MutableLiveData<FirebaseUser?>
        fun logOut()

        fun removeBookmarksListener()
    }
}