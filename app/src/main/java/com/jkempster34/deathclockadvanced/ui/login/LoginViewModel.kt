package com.jkempster34.deathclockadvanced.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthCredential
import com.jkempster34.deathclockadvanced.data.User
import com.jkempster34.deathclockadvanced.data.auth.AuthRepository


class LoginViewModel @ViewModelInject constructor(
    private var authRepository: AuthRepository,
) : ViewModel() {

    lateinit var authenticatedUserLiveData: MutableLiveData<User>

    fun signInWithGoogle(googleAuthCredential: AuthCredential) {
        authenticatedUserLiveData = authRepository.signInWithGoogle(googleAuthCredential)
    }
}
