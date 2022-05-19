package com.example.news.ui.news_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import com.example.news.R
import com.example.news.data.objects.RequestParam
import com.example.news.databinding.FragmentNewsListBinding
import com.example.news.ui.news_detail.NewsDetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment(),
    NewsAdapter.OnItemClickListener,
    NewsCategoriesAdapter.OnItemClickListener {

    private lateinit var binding: FragmentNewsListBinding
    private val adapterNews = NewsAdapter(this)
    private val adapterCategories = NewsCategoriesAdapter(this)
    private val newsViewModel by viewModel<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        showNews()
    }

    private fun initRecycler() {
        binding.newsRecycler.adapter = adapterNews
        binding.newsCategoryRecycler.adapter = adapterCategories
    }

    private fun showNews() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            newsViewModel.loadNews()
        }

        newsViewModel.categoriesList.observe(viewLifecycleOwner) {
            adapterCategories.addData(it)
        }

        newsViewModel.newsList.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            adapterNews.addData(it)
        }
    }

    override fun onItemNewsClick(url: String) {
        val fragmentDetail = NewsDetailFragment.newInstance(url)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.newsFrameLayout, fragmentDetail)
            .addToBackStack(fragmentDetail.javaClass.name)
            .commit()
    }

    override fun onImageShareClick(url: String) {
        ShareCompat.IntentBuilder(requireContext())
            .setType("text/plain")
            .setChooserTitle(R.string.share_url)
            .setText(url)
            .startChooser()
    }

    override fun onItemCategoryClick(category: String) {
        RequestParam.CATEGORY = category
        binding.swipeRefreshLayout.isRefreshing = true
        newsViewModel.loadNews()
    }
}