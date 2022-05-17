package com.example.news.ui.news_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.FragmentNewsListBinding
import com.example.news.ui.news_detail.NewsDetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment(), NewsAdapter.OnItemClickListener {

    private lateinit var binding: FragmentNewsListBinding
    private val adapter = NewsAdapter(this)
    private val newsViewModel by viewModel<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        showNews()
    }

    private fun initRecycler() {
        binding.newsRecyclerView.layoutManager =
            LinearLayoutManager(
                binding.newsRecyclerView.context, RecyclerView.VERTICAL, false
            )
        binding.newsRecyclerView.adapter = adapter
    }

    private fun showNews() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            newsViewModel.loadNews()
        }

        newsViewModel.newsList.observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            adapter.addData(it)
        }
    }

    override fun onItemClick(url: String) {
        val fragmentDetail = NewsDetailFragment.newInstance(url)
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.newsFrameLayout, fragmentDetail)
            ?.addToBackStack(fragmentDetail.javaClass.name)
            ?.commit()
    }
}