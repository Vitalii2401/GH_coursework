package com.example.news.ui.news_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news.databinding.FragmentNewsListBinding

class NewsListFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = NewsListFragment()
    }
}