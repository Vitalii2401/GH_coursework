package com.example.news.ui.news_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.data.objects.model.NewsCategory
import com.example.news.databinding.CategoriesListItemBinding

class NewsCategoriesAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<NewsCategoriesAdapter.NewsCategoriesViewHolder>() {

    private lateinit var binding: CategoriesListItemBinding
    private var newsCategoriesList = listOf<NewsCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCategoriesViewHolder {
        binding =
            CategoriesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsCategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsCategoriesViewHolder, position: Int) {
        holder.bind(newsCategoriesList[position])
    }

    override fun getItemCount(): Int = newsCategoriesList.size

    fun addData(list: List<NewsCategory>){
        newsCategoriesList = list
        notifyDataSetChanged()
    }

    inner class NewsCategoriesViewHolder(private val binding: CategoriesListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(current: NewsCategory) {
            binding.iconCategory.setImageResource(current.icon)
            binding.nameCategory.text = current.name
        }

        override fun onClick(p0: View?) {
            val category = newsCategoriesList[adapterPosition].name
            listener.onItemCategoryClick(category)
        }
    }

    interface OnItemClickListener {
        fun onItemCategoryClick(category: String)
    }
}