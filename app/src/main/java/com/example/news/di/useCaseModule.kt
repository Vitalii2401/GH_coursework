package com.example.news.di

import com.example.news.domain.usecase.GetNewsListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetNewsListUseCase(newsRepository = get())
    }
}