package com.example.news.domain.usecase

import androidx.lifecycle.LiveData
import com.example.news.domain.repository.NewsRepository
import com.example.news.ui.tabs.bookmarks.BookmarksModel

class GetListBookmarksUseCase(
    private val newsRepository: NewsRepository
) {
    fun execute(): LiveData<List<BookmarksModel>> {
        return newsRepository.getListBookmarks()
    }

    fun removeBookmarksListener() {
        newsRepository.removeBookmarksListener()
    }
}