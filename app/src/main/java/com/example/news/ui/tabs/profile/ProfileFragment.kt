package com.example.news.ui.tabs.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.FragmentProfileBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val firebaseUser = Firebase.auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUi()
        logOut()
    }

    private fun updateUi() {
        Glide.with(binding.userImage.context)
            .load(firebaseUser?.photoUrl)
            .circleCrop()
            .into(binding.userImage)

        binding.userName.text = firebaseUser?.displayName
        binding.userMail.text = firebaseUser?.email
    }

    private fun logOut() {
        binding.signOutButton.setOnClickListener {
            AuthUI.getInstance().signOut(requireContext()).addOnSuccessListener {
                Log.d("Click logout", "logOut: true")
                findNavController().navigate(R.id.signInFragment)
            }
        }
    }
}