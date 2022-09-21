package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository

class DeleteNewsFromBookmarksUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(id: String) {
        newsRepository.deleteNewsFromBookmarks(id)
    }
}