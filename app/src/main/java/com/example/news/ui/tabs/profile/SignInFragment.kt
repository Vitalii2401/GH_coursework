package com.example.news.ui.tabs.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.navigation.fragment.findNavController
import com.example.news.R
import com.example.news.databinding.FragmentSignInBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

var firebaseUser = Firebase.auth.currentUser


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private val signInLauncher =
        registerForActivityResult(FirebaseAuthUIActivityResultContract()) { res ->
            this.signInResult(res)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkAuthState()

        binding.signInButton.setOnClickListener {
            showFirebaseUI()
        }
    }

    private fun showFirebaseUI() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .build()

        signInLauncher.launch(signInIntent)
    }

    private fun signInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == ComponentActivity.RESULT_OK) {
            firebaseUser = Firebase.auth.currentUser
            checkAuthState()
        }
    }

    private fun checkAuthState() {
        if (firebaseUser != null) {
            findNavController().navigate(R.id.profileFragment)
        }
    }
}