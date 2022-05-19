package com.example.news.domain.repository

import com.example.news.domain.model.NewsDomainModel

interface NewsRepository {
    suspend fun fetchNews(): List<NewsDomainModel>
    suspend fun loadNews(): List<NewsDomainModel>
}