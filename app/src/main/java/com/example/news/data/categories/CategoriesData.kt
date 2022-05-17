package com.example.news.data.categories

import com.example.news.NewsApp
import com.example.news.R

object CategoriesData {
    val categoriesList = listOf<NewsCategory>(
        NewsCategory(
            icon = R.drawable.ic_general,
            name = NewsApp.resourses.getString(R.string.general)
        ),
        NewsCategory(
            icon = R.drawable.ic_business,
            name = NewsApp.resourses.getString(R.string.business)
        ),
        NewsCategory(
            icon = R.drawable.ic_entertainment,
            name = NewsApp.resourses.getString(R.string.entertainment)
        ),
        NewsCategory(
            icon = R.drawable.ic_health,
            name = NewsApp.resourses.getString(R.string.health)
        ),
        NewsCategory(
            icon = R.drawable.ic_science,
            name = NewsApp.resourses.getString(R.string.science)
        ),
        NewsCategory(
            icon = R.drawable.ic_sports,
            name = NewsApp.resourses.getString(R.string.sports)
        ),
        NewsCategory(
            icon = R.drawable.ic_technology,
            name = NewsApp.resourses.getString(R.string.technology)
        )
    )
}