package com.jkempster34.deathclockadvanced.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jkempster34.deathclockadvanced.data.User
import javax.inject.Inject

class DefaultAuthRepository : AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun getCurrentUser(): User? {
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        return if (firebaseUser == null) {
            null
        } else {
            User(
                firebaseUser.uid,
                firebaseUser.displayName,
                firebaseUser.email
            )
        }
    }
}

