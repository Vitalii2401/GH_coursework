package com.example.news.data.api.mapper

import com.example.news.data.api.ArticlesResponse
import com.example.news.domain.model.NewsDomainModel

fun mapNewsToDomain(news: ArticlesResponse) = with(news) {
    NewsDomainModel(
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
    )
}