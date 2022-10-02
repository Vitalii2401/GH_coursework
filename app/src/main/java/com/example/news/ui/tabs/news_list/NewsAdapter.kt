package com.example.news.ui.tabs.news_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.NewsListItemBinding
import com.example.news.domain.model.NewsDomainModel

class NewsAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var binding: NewsListItemBinding
    private var newsList = listOf<NewsDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = NewsListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])

        holder.itemView.setOnClickListener {
            newsList[position].url?.let {
                listener.onItemNewsClick(it)
            }
        }

        holder.itemView.findViewById<ImageButton>(R.id.iconShare).setOnClickListener {
            newsList[position].url?.let {
                listener.onImageShareClick(it)
            }
        }

        holder.itemView.findViewById<ImageButton>(R.id.addBookmarkButton).setOnClickListener {
            listener.onAddBookmarkClick(newsList[position])
        }
    }

    override fun getItemCount(): Int = newsList.size

    class NewsViewHolder(private val binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(current: NewsDomainModel) {
            binding.newsTitle.text = current.title
            Glide.with(binding.newsImageView.context)
                .load(current.urlToImage)
                .error(R.drawable.image_icon)
                .into(binding.newsImageView)
        }
    }

    fun addData(list: List<NewsDomainModel>) {
        newsList = list
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemNewsClick(url: String)
        fun onImageShareClick(url: String)
        fun onAddBookmarkClick(news: NewsDomainModel)
    }
}