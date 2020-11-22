package com.jkempster34.myfive.data.auth

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.jkempster34.myfive.data.User

interface AuthRepository {
    fun getCurrentUser(): User?
    fun signInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<User>
    fun signOut()
}