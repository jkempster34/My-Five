package com.jkempster34.deathclockadvanced.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.jkempster34.deathclockadvanced.data.auth.AuthStateLiveData

class AuthStateViewModel @ViewModelInject constructor(
    authStateLiveData: AuthStateLiveData
) : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    val authenticationState = authStateLiveData.map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}