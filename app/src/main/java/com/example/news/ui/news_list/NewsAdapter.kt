package com.example.news.ui.news_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.NewsListItemBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var binding: NewsListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = NewsListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class NewsViewHolder(private val binding: NewsListItemBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(){

            }
    }
}