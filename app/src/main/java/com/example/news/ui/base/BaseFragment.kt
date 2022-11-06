package com.example.news.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorConnectionState.observe(viewLifecycleOwner) { error ->
            if (error) {
                showToast("No internet connection")
                isError()
            }
        }
    }

    abstract fun isError()

    private fun showToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }
}