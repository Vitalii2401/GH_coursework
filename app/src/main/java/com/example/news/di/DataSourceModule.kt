package com.example.news.di

import com.example.news.data.api.datasource.NewsRemoteDataSourceImpl
import com.example.news.data.database.datasource.NewsLocalDataSourceImpl
import com.example.news.data.datasource.NewsDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<NewsDataSource.Remote> {
        NewsRemoteDataSourceImpl(newsApi = get())
    }

    single<NewsDataSource.Local> {
        NewsLocalDataSourceImpl(newsDao = get())
    }
}