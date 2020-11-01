package com.jkempster34.deathclockadvanced.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.jkempster34.deathclockadvanced.data.User
import com.jkempster34.deathclockadvanced.data.firebase.AuthRepository

class MainViewModel @ViewModelInject constructor(
    private var authRepository: AuthRepository
) : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    fun isUserAuthenticated(): AuthenticationState {
        var user: User? = authRepository.getCurrentUser()
        return if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}