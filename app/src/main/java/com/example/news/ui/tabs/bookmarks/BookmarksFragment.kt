package com.example.news.ui.tabs.bookmarks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.news.R
import com.example.news.databinding.FragmentBookmarksBinding
import com.example.news.ui.tabs.TabsFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(), BookmarksAdapter.OnItemClickListenerBookmarks {

    private lateinit var binding: FragmentBookmarksBinding
    private val adapterNews = BookmarksAdapter(this)
    private val bookmarksViewModel by viewModel<BookmarksViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsListRecycler.adapter = adapterNews

        showBookmarks()
        showToastWithResult()
    }

    private fun showBookmarks() {
        bookmarksViewModel.getBookmarks().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.bookmarksProgressBar.visibility = View.GONE
                binding.bookmarksErrorTextView.visibility = View.GONE
            }
            adapterNews.addData(it)
        }

        bookmarksViewModel.getCountBookmarks().observe(viewLifecycleOwner) {
            with(binding) {
                if (it == 0L) {
                    bookmarksErrorTextView.visibility = View.VISIBLE
                    bookmarksProgressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun showToastWithResult() {
        bookmarksViewModel.result.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                bookmarksViewModel.clearResult()
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

    override fun onShareImageClick(url: String) {
        ShareCompat.IntentBuilder(requireContext())
            .setType("text/plain")
            .setChooserTitle("Share url")
            .setText(url)
            .startChooser()
    }

    override fun onItemDelete(bookmarksId: String) {
        bookmarksViewModel.deleteBookmark(bookmarksId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bookmarksViewModel.removeBookmarksListener()
    }
}