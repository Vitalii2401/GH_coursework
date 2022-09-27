package com.example.news.di

import com.example.news.data.api.datasource.NewsRemoteDataSourceImpl
import com.example.news.data.database.datasource.NewsLocalDataSourceImpl
import com.example.news.data.datasource.NewsDataSource
import com.example.news.data.firebase.FirebaseDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<NewsDataSource.Remote> {
        NewsRemoteDataSourceImpl(newsApi = get(), context = get())
    }

    single<NewsDataSource.Local> {
        NewsLocalDataSourceImpl(newsDao = get())
    }

    single<NewsDataSource.Firebase> {
        FirebaseDataSourceImpl()
    }
}