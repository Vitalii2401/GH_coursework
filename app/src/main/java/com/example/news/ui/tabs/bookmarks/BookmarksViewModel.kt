package com.example.news.ui.tabs.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.usecase.DeleteNewsFromBookmarksUseCase
import com.example.news.domain.usecase.GetListBookmarksUseCase
import kotlinx.coroutines.launch

class BookmarksViewModel(
    private val getListBookmarksUseCase: GetListBookmarksUseCase,
    private val deleteNewsFromBookmarksUseCase: DeleteNewsFromBookmarksUseCase,
) : ViewModel() {
    private val _listBookmarks = MutableLiveData<List<BookmarksModel>>()
    val listBookmarks: LiveData<List<BookmarksModel>> = _listBookmarks

    fun getBookmarks(): LiveData<List<BookmarksModel>> {
        return getListBookmarksUseCase.execute()
    }

    fun deleteBookmark(id: String) {
        viewModelScope.launch {
            deleteNewsFromBookmarksUseCase.execute(id)
            getBookmarks()
        }
    }

    fun removeBookmarksListener() {
        getListBookmarksUseCase.removeBookmarksListener()
    }
}