package com.example.news.di

import com.example.news.ui.tabs.news_list.NewsViewModel
import com.example.news.ui.tabs.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NewsViewModel(
            getNewsListUseCase = get(),
            addNewsToBookmarksUseCase = get()
        )
        ProfileViewModel(
            getFirebaseUserUseCase = get(),
            setFirebaseUserUseCase = get()
        )
    }
}