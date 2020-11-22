package com.jkempster34.deathclockadvanced.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.jkempster34.deathclockadvanced.data.auth.AuthRepository

class MainViewModel @ViewModelInject constructor(
    private var authRepository: AuthRepository,
) : ViewModel() {

    fun signOut() {
        authRepository.signOut();
    }
}