package com.example.news.data.firebase

import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDataSourceImpl : NewsDataSource.Firebase {

    private var firebaseUser = Firebase.auth.currentUser
    private val firebaseDatabase = Firebase.database

    override suspend fun saveNews(news: NewsDomainModel): String {
        var result = ""

        databaseReference()?.push()?.setValue(news)
            ?.addOnSuccessListener {
                result = "News add to bookmark"
            }
            ?.addOnFailureListener {
                result = "Failure: ${it.message}"
            }

        return result
    }

    override suspend fun deleteNews(id: String) {
        databaseReference()?.child(id)?.removeValue()
    }

    override suspend fun getListNews(): List<BookmarksModel> {
        val listNews = mutableListOf<BookmarksModel>()

        databaseReference()?.get()?.addOnSuccessListener { dataSnapshot ->
            for(postSnapshot in dataSnapshot.children) {
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
        } ?.addOnFailureListener {
            listNews.clear()
        }

        return listNews
    }

    override suspend fun getUser(): FirebaseUser? {
        return firebaseUser
    }

    override suspend fun setUser(user: FirebaseUser?) {
        firebaseUser = user
    }

    private fun databaseReference() : DatabaseReference? = firebaseUser?.let {
        firebaseDatabase.reference
        .child("users")
        .child(it.uid)
        .child("bookmarksNews")
    }
}