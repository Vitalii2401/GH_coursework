package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository
import com.example.news.ui.tabs.bookmarks.BookmarksModel

class GetListBookmarksUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(): List<BookmarksModel> {
        return newsRepository.getListBookmarks()
    }
}