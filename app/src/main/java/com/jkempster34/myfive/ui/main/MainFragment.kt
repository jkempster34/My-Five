package com.jkempster34.myfive.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jkempster34.myfive.databinding.FragmentMainBinding
import com.jkempster34.myfive.ui.AuthStateViewModel
import com.jkempster34.myfive.ui.AuthStateViewModel.AuthenticationState.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val authStateViewModel: AuthStateViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.authStateViewmodel = authStateViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeAuthenticationState()
    }

    private fun observeAuthenticationState() {
        authStateViewModel.authenticationState.observe(viewLifecycleOwner, { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> {
                    Log.i(TAG, "Authenticated")
                }
                UNAUTHENTICATED -> {
                    Log.i(TAG, "Unauthenticated")
                    navigateToLoginFragment()
                }
            }
        })
    }

    fun signOut() {
        mainViewModel.signOut()
    }

    private fun navigateToLoginFragment() {
        val action = MainFragmentDirections.actionMainFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}