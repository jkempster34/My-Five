package com.jkempster34.deathclockadvanced.data.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.jkempster34.deathclockadvanced.data.User
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor() : AuthRepository {
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

    override fun signInWithGoogle(googleAuthCredential: AuthCredential): MutableLiveData<User> {
        val authenticatedUserMutableLiveData = MutableLiveData<User>()
        firebaseAuth.signInWithCredential(googleAuthCredential)
            .addOnCompleteListener { authTask: Task<AuthResult> ->
                if (authTask.isSuccessful) {
                    val firebaseUser = firebaseAuth.currentUser
                    if (firebaseUser != null) {
                        var user = User(
                            firebaseUser.uid,
                            firebaseUser.displayName,
                            firebaseUser.email
                        )
                        authenticatedUserMutableLiveData.value = user
                    }
                } else {
                    Log.w(TAG, "signInWithCredential:failure", authTask.exception)
                }
            }
        return authenticatedUserMutableLiveData
    }

    companion object {
        private const val TAG = "Firebase"
    }
}

