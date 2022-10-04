package com.example.news.data.objects

import com.example.news.NewsApp
import com.example.news.R
import com.example.news.data.objects.model.NewsCategory

object CategoriesData {
    val categoriesList = listOf<NewsCategory>(
        NewsCategory(
            icon = R.drawable.ic_category_general,
            name = NewsApp.resourses.getString(R.string.general)
        ),
        NewsCategory(
            icon = R.drawable.ic_category_business,
            name = NewsApp.resourses.getString(R.string.business)
        ),
        NewsCategory(
            icon = R.drawable.ic_category_entertainment,
            name = NewsApp.resourses.getString(R.string.entertainment)
        ),
        NewsCategory(
            icon = R.drawable.ic_category_health,
            name = NewsApp.resourses.getString(R.string.health)
        ),
        NewsCategory(
            icon = R.drawable.ic_category_science,
            name = NewsApp.resourses.getString(R.string.science)
        ),
        NewsCategory(
            icon = R.drawable.ic_category_sports,
            name = NewsApp.resourses.getString(R.string.sports)
        ),
        NewsCategory(
            icon = R.drawable.ic_category_technology,
            name = NewsApp.resourses.getString(R.string.technology)
        )
    )
}