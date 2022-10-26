package com.example.news.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.exceptions.ConnectionException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _errorConnection = MutableLiveData<String>()
    val errorConnection: LiveData<String> = _errorConnection

    fun CoroutineScope.safeLaunch(block: CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: ConnectionException) {
                _errorConnection.value = "No internet connection"
            }
        }
    }
}