package com.example.news.ui.tabs.bookmarks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.news.R
import com.example.news.databinding.FragmentBookmarksBinding
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.TabsFragmentDirections
import com.example.news.ui.tabs.profile.firebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(), BookmarksAdapter.OnItemClickListenerBookmarks {

    private lateinit var binding: FragmentBookmarksBinding
    private val adapterNews = BookmarksAdapter(this)
    private val databaseRef = FirebaseDatabase.getInstance().reference
    private val news = mutableListOf<BookmarksModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsListRecycler.adapter = adapterNews

        getBookmarks()
    }

    private fun getBookmarks() {
        val bookmarksRef = firebaseUser?.uid?.let {
            databaseRef.child("users").child(it).child("bookmarksNews")
        }

        bookmarksRef?.get()?.addOnSuccessListener {
            for (postSnapshot in it.children) {
                news.add(
                    BookmarksModel(
                        id = postSnapshot.key.toString(),
                        description = postSnapshot.child("description").value.toString(),
                        publishedAt = postSnapshot.child("publishedAt").value.toString(),
                        title = postSnapshot.child("title").value.toString(),
                        url = postSnapshot.child("url").value.toString(),
                        urlToImage = postSnapshot.child("urlToImage").value.toString(),
                    )
                )
            }
            adapterNews.addData(news)
        }
            ?.addOnFailureListener {
                news.clear()
                adapterNews.addData(news)
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

    override fun onItemDelete(bookmarkId: String) {
        val bookmarksRef = firebaseUser?.uid?.let {
            databaseRef.child("users").child(it).child("bookmarksNews").child(bookmarkId)
        }
        bookmarksRef?.removeValue()?.addOnSuccessListener {
            Toast.makeText(requireContext(), "News delete", Toast.LENGTH_SHORT).show()
        }
    }
}