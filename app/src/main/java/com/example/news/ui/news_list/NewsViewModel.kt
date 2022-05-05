package com.example.news.ui.news_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.model.NewsDomainModel
import com.example.news.domain.usecase.GetNewsListUseCase
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {

    init {
        loadNews()
    }

    private val _newsList = MutableLiveData<List<NewsDomainModel>>()
    val newsList: LiveData<List<NewsDomainModel>> = _newsList

    private fun loadNews() {
        viewModelScope.launch {
            _newsList.value = getNewsListUseCase.execute()
        }
    }
}