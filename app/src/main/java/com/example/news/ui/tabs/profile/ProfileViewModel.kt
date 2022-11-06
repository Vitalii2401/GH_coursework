package com.example.news.ui.tabs.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.domain.usecase.FirebaseLogOutUseCase
import com.example.news.domain.usecase.GetFirebaseUserUseCase
import com.example.news.domain.usecase.SetFirebaseUserUseCase
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel(
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase,
    private val setFirebaseUserUseCase: SetFirebaseUserUseCase,
    private val firebaseLogOutUseCase: FirebaseLogOutUseCase
) : ViewModel() {

    fun getUser(): MutableLiveData<FirebaseUser?> {
        return getFirebaseUserUseCase.execute()
    }

    fun setUser(user: FirebaseUser?) {
        setFirebaseUserUseCase.execute(user)
    }

    fun logOut() {
        firebaseLogOutUseCase.execute()
    }
}