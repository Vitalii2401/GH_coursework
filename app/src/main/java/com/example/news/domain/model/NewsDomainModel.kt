package com.example.news.domain.model

data class NewsDomainModel(
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
)
