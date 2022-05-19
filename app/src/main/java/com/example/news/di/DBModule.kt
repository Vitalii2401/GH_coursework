package com.example.news.di

import androidx.room.Room
import com.example.news.R
import com.example.news.data.database.NewsDataBase
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(get(), NewsDataBase::class.java, R.string.db_name.toString())
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { get<NewsDataBase>().getNewsDao() }
}