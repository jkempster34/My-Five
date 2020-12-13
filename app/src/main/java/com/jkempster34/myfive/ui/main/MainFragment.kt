package com.jkempster34.myfive.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jkempster34.myfive.databinding.FragmentMainBinding
import com.jkempster34.myfive.ui.AuthStateViewModel
import com.jkempster34.myfive.ui.BaseFragment

class MainFragment : BaseFragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {}

    fun signOut() {
        mainViewModel.signOut()
        navigateToFragment(MainFragmentDirections.actionMainFragmentToLoginFragment())
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}