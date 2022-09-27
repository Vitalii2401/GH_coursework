package com.example.news.di

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {
    single<FirebaseUser?> {
        Firebase.auth.currentUser
    }

    single<FirebaseDatabase> {
        Firebase.database
    }
}