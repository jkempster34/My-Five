package com.jkempster34.deathclockadvanced.data.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.jkempster34.deathclockadvanced.data.User

interface AuthRepository {
    fun getCurrentUser(): User?
    fun signInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<User>
}