package com.example.news.data.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDataSourceImpl() : NewsDataSource.Firebase {

    private var firebaseUser = Firebase.auth.currentUser
    private val firebaseDatabase = Firebase.database
    private val listNews = mutableListOf<BookmarksModel>()
    private val listBookmarks = MutableLiveData<List<BookmarksModel>>()

    override fun addNewsToBookmarks(news: NewsDomainModel, resultLiveData: MutableLiveData<String>) {

        databaseReference()?.let {  dbReference ->
            dbReference.push().setValue(news)
                .addOnSuccessListener {
                    resultLiveData.value = "\"${news.title}\" \nadd to bookmark"
                }
                .addOnFailureListener {
                    resultLiveData.value = "Failure: ${it.message}"
                }
        } ?: let {
            resultLiveData.value = "Please log in"
        }
    }

    override suspend fun deleteNewsFromBookmarks(id: String) {
        databaseReference()?.child(id)?.removeValue()
    }

    override fun removeBookmarksListener() {
        databaseReference()?.removeEventListener(bookmarksListener)
    }

    override fun getListBookmarks(): LiveData<List<BookmarksModel>> {
        databaseReference()?.addValueEventListener(bookmarksListener)
        return listBookmarks
    }

    override suspend fun getUser(): FirebaseUser? {
        return firebaseUser
    }

    override suspend fun setUser(user: FirebaseUser?) {
        firebaseUser = user
    }

    private fun databaseReference() : DatabaseReference? = firebaseUser?.let { user ->
        firebaseDatabase.reference
        .child("users")
        .child(user.uid)
        .child("bookmarksNews")
    }

    private val bookmarksListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            listNews.clear()
            for(postSnapshot in snapshot.children) {
                listNews.add(
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
            listBookmarks.value = listNews
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }
}
