package com.example.news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.data.database.dao.NewsDao
import com.example.news.data.database.entites.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}