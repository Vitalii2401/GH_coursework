package com.example.news.data.database.mapper

import com.example.news.data.database.entites.NewsEntity
import com.example.news.domain.model.NewsDomainModel

fun mapNewsDomainModelToEntity(newsDomainModel: NewsDomainModel) = with(newsDomainModel) {
    NewsEntity(
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
    )
}

fun mapEntityToNewsDomainModel(newsEntity: NewsEntity) = with(newsEntity) {
    NewsDomainModel(
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
    )
}