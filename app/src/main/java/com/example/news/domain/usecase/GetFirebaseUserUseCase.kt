package com.example.news.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.news.domain.repository.NewsRepository
import com.google.firebase.auth.FirebaseUser

class GetFirebaseUserUseCase(
    private val newsRepository: NewsRepository
) {
    fun execute(): MutableLiveData<FirebaseUser?> {
        return newsRepository.getFirebaseUser()
    }
}