package com.example.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.news.R
import com.example.news.ui.news_list.NewsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = NewsListFragment()

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.newsFrameLayout, fragment)
                .commit()
        }
    }
}