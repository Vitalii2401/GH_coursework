package com.example.news.ui.tabs.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.domain.usecase.GetFirebaseUserUseCase
import com.example.news.domain.usecase.SetFirebaseUserUseCase
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase,
    private val setFirebaseUserUseCase: SetFirebaseUserUseCase
) : ViewModel() {

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: LiveData<FirebaseUser?> = _firebaseUser

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            _firebaseUser.value = getFirebaseUserUseCase.execute()
        }
    }

    fun setUser(user: FirebaseUser?) {
        viewModelScope.launch {
            setFirebaseUserUseCase.execute(user)
            getUser()
        }
    }
}