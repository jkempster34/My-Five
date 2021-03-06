package com.jkempster34.myfive.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.jkempster34.myfive.databinding.FragmentLoginBinding
import com.jkempster34.myfive.ui.AuthStateViewModel
import com.jkempster34.myfive.ui.AuthStateViewModel.AuthenticationState.AUTHENTICATED
import com.jkempster34.myfive.ui.AuthStateViewModel.AuthenticationState.UNAUTHENTICATED
import javax.inject.Inject

class LoginFragment : Fragment() {
    private val authStateViewModel: AuthStateViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeAuthenticationState()
    }

    private fun observeAuthenticationState() {
        authStateViewModel.authenticationState.observe(viewLifecycleOwner, { authenticationState ->
            when (authenticationState) {
                AUTHENTICATED -> {
                    Log.i(TAG, "Authenticated")
                    // TODO: Get user, if new show onboarding
                    navigateToMainFragment()
                }
                UNAUTHENTICATED -> {
                    Log.i(TAG, "Unauthenticated")
                }
            }
        })
    }

    enum class RequestCodes(val value: Int) {
        GOOGLE_SIGN_IN(1),
    }

    fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RequestCodes.GOOGLE_SIGN_IN.value)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RequestCodes.GOOGLE_SIGN_IN.value) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val authCredential = getGoogleAuthCredential(account);
                signInWithGoogleAuthCredential(authCredential)
            } catch (error: ApiException) {
                Log.w(TAG, "Google sign in failed", error)
            }
            // TODO:
//        } else if (requestCode == FACEBOOK_LOG_IN_REQUEST_CODE) {
//            mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data)
//        } else if (requestCode == TWITTER_LOG_IN_REQUEST_CODE) {
//            mTwitterLoginButton.onActivityResult(requestCode, resultCode, data)
//        }
        }
    }

    private fun getGoogleAuthCredential(googleSignInAccount: GoogleSignInAccount): AuthCredential {
        val googleIdToken = googleSignInAccount.idToken
        return GoogleAuthProvider.getCredential(googleIdToken, null)
    }

    private fun signInWithGoogleAuthCredential(googleAuthCredential: AuthCredential) {
        loginViewModel.signInWithGoogle(googleAuthCredential)
    }


    private fun navigateToMainFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}