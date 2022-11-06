package com.example.news.ui.tabs.news_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.news.R
import com.example.news.data.objects.RequestParam
import com.example.news.databinding.FragmentNewsListBinding
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.base.BaseFragment
import com.example.news.ui.tabs.TabsFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : BaseFragment(),
    NewsAdapter.OnItemClickListener,
    NewsCategoriesAdapter.OnItemClickListener {

    private lateinit var binding: FragmentNewsListBinding
    private val adapterNews = NewsAdapter(this)
    private val adapterCategories = NewsCategoriesAdapter(this)
    override val viewModel by viewModel<NewsViewModel>()

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
        update()
        showToastWithResult()
    }

    override fun isError() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun initRecycler() {
        binding.newsRecycler.adapter = adapterNews
        binding.newsCategoryRecycler.adapter = adapterCategories
    }

    private fun showNews() {
        viewModel.newsList.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            adapterNews.addData(it)
        }

        viewModel.categoriesList.observe(viewLifecycleOwner) {
            adapterCategories.addData(it)
        }
    }

    private fun update() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadNews()
        }
    }

    private fun showToastWithResult() {
        viewModel.result.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.clearResult()
            }
        }
    }

    override fun onItemNewsClick(url: String) {
        val topLevelHost = requireActivity().supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment

        topLevelHost.navController.navigate(
            TabsFragmentDirections.actionTabsFragmentToNewsDetailFragment(
                url
            )
        )
    }

    override fun onImageShareClick(url: String) {
        ShareCompat.IntentBuilder(requireContext())
            .setType("text/plain")
            .setChooserTitle(R.string.share_url)
            .setText(url)
            .startChooser()
    }

    override fun onAddBookmarkClick(news: NewsDomainModel) {
        viewModel.addToBookmarks(news)
    }

    override fun onItemCategoryClick(category: String) {
        RequestParam.CATEGORY = category
        binding.swipeRefreshLayout.isRefreshing = true
        viewModel.loadNews()
        binding.newsRecycler.layoutManager?.scrollToPosition(0)
    }
}