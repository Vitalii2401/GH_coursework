package com.example.news.di

import com.example.news.data.repository.NewsRepositoryImpl
import com.example.news.domain.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl(
            newsRemoteDataSource = get(),
            newsLocalDataSource = get(),
            firebaseDataSource = get()
        )
    }
}