package com.example.news.ui.news_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.data.api.NewsResponse

class NewsViewModel : ViewModel() {

    init {
        loadNews()
    }

    private val _newsList = MutableLiveData<List<NewsResponse>>()
    val newsList = _newsList

    private fun loadNews() {

    }
}