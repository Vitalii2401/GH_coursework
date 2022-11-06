package com.example.news.data.firebase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.R
import com.example.news.data.datasource.NewsDataSource
import com.example.news.domain.model.NewsDomainModel
import com.example.news.ui.tabs.bookmarks.BookmarksModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDataSourceImpl(
    private val context: Context
) : NewsDataSource.Firebase {

    private val firebaseDatabase = Firebase.database
    private var firebaseUser = Firebase.auth.currentUser

    private val listBookmarks = mutableListOf<BookmarksModel>()
    private val countBookmarksLiveData = MutableLiveData<Long>()
    private val listBookmarksLiveData = MutableLiveData<List<BookmarksModel>>()
    private val firebaseUserLiveData = MutableLiveData<FirebaseUser?>(firebaseUser)

    /* Bookmarks */
    override fun getListBookmarks(): LiveData<List<BookmarksModel>> {
        databaseReference()
            ?.addValueEventListener(bookmarksListener)
            ?: let {
                listBookmarks.clear()
                listBookmarksLiveData.value = listBookmarks
            }
        return listBookmarksLiveData
    }

    override fun getCountBookmarks(): LiveData<Long> {
        return countBookmarksLiveData
    }

    override fun addNewsToBookmarks(
        news: NewsDomainModel,
        resultLiveData: MutableLiveData<String>
    ) {

        databaseReference()?.let { dbReference ->
            dbReference.push().setValue(news)
                .addOnSuccessListener {
                    resultLiveData.value =
                        context.getString(R.string.result_success_added_to_bookmarks, news.title)
                }
                .addOnFailureListener {
                    resultLiveData.value =
                        context.getString(R.string.result_failure_added_to_bookmarks, it.message)
                }
        } ?: let {
            resultLiveData.value = context.getString(R.string.result_log_in)
        }
    }

    override fun deleteNewsFromBookmarks(
        id: String,
        resultLiveData: MutableLiveData<String>
    ) {
        databaseReference()?.let { dbReference ->
            dbReference.child(id).removeValue()
                .addOnSuccessListener {
                    resultLiveData.value =
                        context.getString(R.string.result_success_deleted_from_bookmarks)
                }
                .addOnFailureListener {
                    resultLiveData.value =
                        context.getString(R.string.result_failure_deleted_from_bookmarks)
                }
        }
    }

    override fun removeBookmarksListener() {
        databaseReference()?.removeEventListener(bookmarksListener)
    }

    /* Account */
    override fun setUser(user: FirebaseUser?) {
        firebaseUser = user
        firebaseUserLiveData.value = firebaseUser
    }

    override fun getUser(): MutableLiveData<FirebaseUser?> {
        return firebaseUserLiveData
    }

    override fun logOut() {
        AuthUI.getInstance().signOut(context).addOnSuccessListener {
            firebaseUser = Firebase.auth.currentUser
            firebaseUserLiveData.value = firebaseUser
        }
    }

    /* Database */
    private fun databaseReference(): DatabaseReference? = firebaseUser?.let { user ->
        firebaseDatabase.reference
            .child("users")
            .child(user.uid)
            .child("bookmarksNews")
    }

    /* Bookmarks listener */
    private val bookmarksListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            countBookmarksLiveData.value = snapshot.childrenCount
            listBookmarks.clear()
            for (postSnapshot in snapshot.children) {
                listBookmarks.add(
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
            listBookmarksLiveData.value = listBookmarks
        }

        override fun onCancelled(error: DatabaseError) {
            listBookmarks.clear()
            listBookmarksLiveData.value = listBookmarks
        }
    }
}
