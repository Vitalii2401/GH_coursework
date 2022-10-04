package com.example.news.ui.news_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.example.news.databinding.FragmentNewsDetailBinding

private const val URL = "url"

class NewsDetailFragment : Fragment() {
    private var url: String? = null
    private lateinit var binding: FragmentNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showNews()
    }

    private fun showNews() {
        binding.webView.webViewClient = WebViewClient()
        url?.let { binding.webView.loadUrl(it) }

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.isVisible = false
            }
        }
    }

    /*companion object {
        @JvmStatic
        fun newInstance(url: String) =
            NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                }
            }
    }*/
}