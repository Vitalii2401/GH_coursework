package com.example.news.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.data.database.entites.NewsEntity

@Dao
abstract class NewsDao {
    @Query("SELECT * FROM news")
    abstract suspend fun fetchNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addNews(news: List<NewsEntity>)
}