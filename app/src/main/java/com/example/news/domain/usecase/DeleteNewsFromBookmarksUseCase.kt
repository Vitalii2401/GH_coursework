package com.example.news.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.news.domain.repository.NewsRepository

class DeleteNewsFromBookmarksUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(id: String, resultLiveData: MutableLiveData<String>) {
        newsRepository.deleteNewsFromBookmarks(id, resultLiveData)
    }
}