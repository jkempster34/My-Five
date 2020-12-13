package com.jkempster34.myfive.ui.splash

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jkempster34.myfive.R
import com.jkempster34.myfive.ui.AuthStateViewModel
import com.jkempster34.myfive.ui.BaseFragment

class SplashFragment : BaseFragment() {
    private val authStateViewModel: AuthStateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeAuthenticationState()
    }

    private fun observeAuthenticationState() {
        authStateViewModel.authenticationState.observe(viewLifecycleOwner, { authenticationState ->
            when (authenticationState) {
                AuthStateViewModel.AuthenticationState.AUTHENTICATED -> {
                    Log.i(TAG, "Authenticated")
                    navigateToFragment(SplashFragmentDirections.actionSplashFragmentToMainFragment())
                }
                AuthStateViewModel.AuthenticationState.UNAUTHENTICATED -> {
                    Log.i(TAG, "Unauthenticated")
                    navigateToFragment(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                }
            }
        })
    }


}