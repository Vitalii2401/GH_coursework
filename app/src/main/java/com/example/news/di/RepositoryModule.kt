package com.example.news.di

import com.example.news.data.repository.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single {
        NewsRepositoryImpl(
            newsRemoteDataSource = get(),
            newsLocalDataSource = get()
        )
    }
}