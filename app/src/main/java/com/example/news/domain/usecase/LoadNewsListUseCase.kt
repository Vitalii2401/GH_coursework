package com.example.news.domain.usecase

import com.example.news.domain.model.NewsDomainModel
import com.example.news.domain.repository.NewsRepository

class LoadNewsListUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(): List<NewsDomainModel> {
        return newsRepository.loadNews()
    }
}