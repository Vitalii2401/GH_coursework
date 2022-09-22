package com.example.news.data.repository

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

    override suspend fun fetchNews(): List<NewsDomainModel> {
        return newsLocalDataSource.fetchNews()
    }

    override suspend fun loadNews() {
        newsRemoteDataSource.loadNews().let {
            if (it.isNotEmpty()) {
                newsLocalDataSource.deleteNews()
                newsLocalDataSource.addNews(it)
            }
        }
    }

    override suspend fun addNewsToBookmarks(news: NewsDomainModel): String {
        return firebaseDataSource.saveNews(news)
    }

    override suspend fun getListBookmarks(): List<BookmarksModel> {
        return firebaseDataSource.getListNews()
    }

    override suspend fun getFirebaseUser(): FirebaseUser? {
        return firebaseDataSource.getUser()
    }

    override suspend fun setFirebaseUser(user: FirebaseUser?) {
        firebaseDataSource.setUser(user)
    }

    override suspend fun deleteNewsFromBookmarks(id: String) {
        firebaseDataSource.deleteNews(id)
    }
}