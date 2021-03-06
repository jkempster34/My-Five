package com.jkempster34.myfive.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.jkempster34.myfive.data.auth.AuthRepository

class MainViewModel @ViewModelInject constructor(
    private var authRepository: AuthRepository,
) : ViewModel() {

    fun signOut() {
        authRepository.signOut();
    }
}