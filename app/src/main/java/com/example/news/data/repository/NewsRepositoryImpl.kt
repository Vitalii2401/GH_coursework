package com.example.news.data.repository

import androidx.lifecycle.LiveData
import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel
import com.example.news.domain.repository.NewsRepository
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.google.firebase.auth.FirebaseUser

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsDataSource.Remote,
    private val newsLocalDataSource: NewsDataSource.Local,
    private val firebaseDataSource: NewsDataSource.Firebase
) : NewsRepository {

    /* Local data source */
    override suspend fun fetchNews(): List<NewsDomainModel> {
        return newsLocalDataSource.fetchNews()
    }

    /* Remote data source */
    override suspend fun loadNews() {
        newsRemoteDataSource.loadNews().let {
            if (it.isNotEmpty()) {
                newsLocalDataSource.deleteNews()
                newsLocalDataSource.addNews(it)
            }
        }
    }

    /* Firebase data source */
    override suspend fun addNewsToBookmarks(news: NewsDomainModel): String {
        return firebaseDataSource.addNewsToBookmarks(news)
    }

    override fun removeBookmarksListener() {
        firebaseDataSource.removeBookmarksListener()
    }

    override fun getListBookmarks(): LiveData<List<BookmarksModel>> {
        return firebaseDataSource.getListBookmarks()
    }

    override suspend fun getFirebaseUser(): FirebaseUser? {
        return firebaseDataSource.getUser()
    }

    override suspend fun setFirebaseUser(user: FirebaseUser?) {
        firebaseDataSource.setUser(user)
    }

    override suspend fun deleteNewsFromBookmarks(id: String) {
        firebaseDataSource.deleteNewsFromBookmarks(id)
    }
}