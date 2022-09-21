package com.example.news.di

import com.example.news.domain.usecase.AddNewsToBookmarksUseCase
import com.example.news.domain.usecase.DeleteNewsFromBookmarksUseCase
import com.example.news.domain.usecase.GetListBookmarksUseCase
import com.example.news.domain.usecase.GetNewsListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetNewsListUseCase(newsRepository = get())
        AddNewsToBookmarksUseCase(newsRepository = get())
        DeleteNewsFromBookmarksUseCase(newsRepository = get())
        GetListBookmarksUseCase(newsRepository = get())
    }
}