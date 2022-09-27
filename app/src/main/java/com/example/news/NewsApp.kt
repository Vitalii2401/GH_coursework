package com.example.news

import android.app.Application
import android.content.res.Resources
import com.example.news.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NewsApp : Application() {

    companion object {
        lateinit var resourses: Resources
    }

    override fun onCreate() {
        super.onCreate()

        resourses = resources

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    repositoryModule,
                    dataSourceModule,
                    //firebaseModule,
                    dbModule,
                    apiModule
                )
            )
        }
    }
}