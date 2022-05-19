package com.example.news

import android.app.Application
import com.example.news.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                viewModelModule,
                useCaseModule,
                repositoryModule,
                dataSourceModule,
                dbModule,
                apiModule,
            ))
        }
    }
}