package com.jkempster34.deathclockadvanced.data.auth

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jkempster34.deathclockadvanced.data.User

class AuthStateLiveData : LiveData<User?>() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    private val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        value = if (firebaseUser != null)
            User(
                firebaseUser.uid,
                firebaseUser.displayName,
                firebaseUser.email
            )
        else null
    }

    override fun onActive() {
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onInactive() {
        firebaseAuth.removeAuthStateListener(authStateListener)
    }
}