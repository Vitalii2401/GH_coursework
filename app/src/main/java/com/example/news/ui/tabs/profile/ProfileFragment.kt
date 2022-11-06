package com.example.news.ui.tabs.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel by viewModel<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logOut()
        checkAuthState()
    }

    private fun checkAuthState() {
        profileViewModel.getUser().observe(viewLifecycleOwner) {
            it?.let { updateUi(it) } ?: findNavController().navigate(R.id.signInFragment)
        }
    }

    private fun updateUi(user: FirebaseUser) {
        Glide.with(binding.userImage.context)
            .load(user.photoUrl)
            .circleCrop()
            .into(binding.userImage)

        binding.userName.text = user.displayName
        binding.userMail.text = user.email
    }

    private fun logOut() {
        binding.signOutButton.setOnClickListener {
            profileViewModel.logOut()
        }
    }
}