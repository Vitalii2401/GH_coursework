package com.example.news.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.news.domain.model.NewsDomainModel
import com.example.news.domain.repository.NewsRepository

class AddNewsToBookmarksUseCase(
    private val newsRepository: NewsRepository
) {
    fun execute(news: NewsDomainModel, resultLiveData: MutableLiveData<String>){
        newsRepository.addNewsToBookmarks(news, resultLiveData)
    }
}