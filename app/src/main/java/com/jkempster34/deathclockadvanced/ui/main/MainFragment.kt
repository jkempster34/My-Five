package com.jkempster34.deathclockadvanced.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jkempster34.deathclockadvanced.R
import com.jkempster34.deathclockadvanced.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel = MainViewModel()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getAuthenticationState()
    }

    private fun getAuthenticationState() {
        val authenticationState = viewModel.isUserAuthenticated()
        if (authenticationState == MainViewModel.AuthenticationState.UNAUTHENTICATED)
            navigateToLoginFragment()
    }

    private fun navigateToLoginFragment() {
        val action = MainFragmentDirections.actionMainFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}