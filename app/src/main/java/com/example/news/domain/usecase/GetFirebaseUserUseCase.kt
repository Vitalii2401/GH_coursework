package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository
import com.google.firebase.auth.FirebaseUser

class GetFirebaseUserUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(): FirebaseUser? {
        return newsRepository.getFirebaseUser()
    }
}