package com.example.news.domain.usecase

import com.example.news.domain.model.NewsDomainModel
import com.example.news.domain.repository.NewsRepository

class AddNewsToBookmarksUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(news: NewsDomainModel): String {
        return newsRepository.addNewsToBookmarks(news)
    }
}