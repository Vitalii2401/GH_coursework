package com.example.news.domain.usecase

import androidx.lifecycle.LiveData
import com.example.news.domain.repository.NewsRepository

class FirebaseGetCountBookmarksUseCase(
    private val newsRepository: NewsRepository
) {
    fun execute(): LiveData<Long> {
        return newsRepository.getCountBookmarks()
    }
}