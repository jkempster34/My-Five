package com.jkempster34.deathclockadvanced.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.jkempster34.deathclockadvanced.R
import com.jkempster34.deathclockadvanced.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )
        binding.fragment = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initGoogleSignInClient()
    }

    enum class RequestCodes(val value: Int) {
        GOOGLE_SIGN_IN(1),
    }

    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), googleSignInOptions);
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
        viewModel.signInWithGoogle(googleAuthCredential)
        viewModel.authenticatedUserLiveData.observe(this) { authenticatedUser ->
//            if (authenticatedUser.isNew) {
//                createNewUser(authenticatedUser)
//            } else {
            Log.i(TAG, "Successfully signed in user ${authenticatedUser.email}")
            navigateToMainFragment()
        }
    }

    private fun navigateToMainFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
        findNavController().navigate(action)
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}