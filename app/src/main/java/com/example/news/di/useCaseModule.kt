package com.example.news.di

import com.example.news.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetNewsListUseCase(newsRepository = get()) }
    factory { AddNewsToBookmarksUseCase(newsRepository = get()) }
    factory { DeleteNewsFromBookmarksUseCase(newsRepository = get()) }
    factory { GetListBookmarksUseCase(newsRepository = get()) }
    factory { GetFirebaseUserUseCase(newsRepository = get()) }
    factory { SetFirebaseUserUseCase(newsRepository = get()) }
    factory { FirebaseLogOutUseCase(newsRepository = get()) }
}