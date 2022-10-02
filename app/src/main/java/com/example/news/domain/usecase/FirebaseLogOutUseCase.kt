package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository

class FirebaseLogOutUseCase(
    private val newsRepository: NewsRepository
) {
    fun execute() {
        newsRepository.logOut()
    }
}