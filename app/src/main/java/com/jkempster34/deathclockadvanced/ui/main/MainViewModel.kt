package com.jkempster34.deathclockadvanced.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jkempster34.deathclockadvanced.data.User
import com.jkempster34.deathclockadvanced.data.firebase.AuthRepository
import com.jkempster34.deathclockadvanced.data.firebase.DefaultAuthRepository

class MainViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository = DefaultAuthRepository()
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