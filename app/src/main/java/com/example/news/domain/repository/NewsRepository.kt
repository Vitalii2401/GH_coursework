package com.example.news.domain.repository

interface NewsRepository {
    fun fetchNews()
    fun loadNews()
}