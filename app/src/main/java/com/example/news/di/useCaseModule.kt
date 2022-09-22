package com.example.news.di

import com.example.news.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetNewsListUseCase(newsRepository = get())
        AddNewsToBookmarksUseCase(newsRepository = get())
        DeleteNewsFromBookmarksUseCase(newsRepository = get())
        GetListBookmarksUseCase(newsRepository = get())
        GetFirebaseUserUseCase(newsRepository = get())
        SetFirebaseUserUseCase(newsRepository = get())
    }
}