package com.example.news.di

import com.example.news.ui.tabs.bookmarks.BookmarksViewModel
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
    }
    viewModel {
        ProfileViewModel(
            getFirebaseUserUseCase = get(),
            setFirebaseUserUseCase = get()
        )
    }

    viewModel {
        BookmarksViewModel(
            getListBookmarksUseCase = get(),
            deleteNewsFromBookmarksUseCase = get()
        )
    }
}