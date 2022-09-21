package com.example.news.data.firebase

import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseDataSourceImpl(
    private val firebaseUser: FirebaseUser,
    private val firebaseDatabase: FirebaseDatabase
) : NewsDataSource.Firebase {

    override suspend fun saveNews(news: NewsDomainModel): String {
        var result = ""

        databaseReference().push().setValue(news)
            .addOnSuccessListener {
                result = "News add to bookmark"
            }
            .addOnFailureListener {
                result = "Failure: ${it.message}"
            }

        return result
    }

    override suspend fun deleteNews(id: String) {
        databaseReference().child(id).removeValue()
    }

    override suspend fun getListNews(): List<BookmarksModel> {
        val listNews = mutableListOf<BookmarksModel>()

        databaseReference().get().addOnSuccessListener { dataSnapshot ->
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
        } .addOnFailureListener {
            listNews.clear()
        }

        return listNews
    }

    private fun databaseReference() : DatabaseReference = firebaseUser.uid
        .let { uid ->
            firebaseDatabase.reference
                .child("users")
                .child(uid)
                .child("bookmarksNews")
    }
}