package com.example.news.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.data.exceptions.ConnectionException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _errorConnectionState = MutableLiveData(false)
    val errorConnectionState: LiveData<Boolean> = _errorConnectionState

    fun CoroutineScope.safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block.invoke(this)
                _errorConnectionState.value = false
            } catch (e: ConnectionException) {
                _errorConnectionState.value = true
            }
        }
    }
}