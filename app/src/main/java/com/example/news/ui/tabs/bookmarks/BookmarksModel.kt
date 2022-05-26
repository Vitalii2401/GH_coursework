package com.example.news.ui.tabs.bookmarks

data class BookmarksModel(
    val id: String,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
)