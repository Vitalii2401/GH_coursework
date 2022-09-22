package com.example.news.domain.usecase

import com.example.news.domain.repository.NewsRepository
import com.google.firebase.auth.FirebaseUser

class SetFirebaseUserUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(user: FirebaseUser) {
        newsRepository.setFirebaseUser(user)
    }
}