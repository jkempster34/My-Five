package com.jkempster34.deathclockadvanced.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.jkempster34.deathclockadvanced.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getAuthenticationState()
    }

    private fun getAuthenticationState() {
        val authenticationState = viewModel.isUserAuthenticated()
        if (authenticationState == MainViewModel.AuthenticationState.UNAUTHENTICATED)
            navigateToLoginFragment()
    }

    fun signOut() {
        val mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        navigateToLoginFragment()
    }

    private fun navigateToLoginFragment() {
        val action = MainFragmentDirections.actionMainFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}