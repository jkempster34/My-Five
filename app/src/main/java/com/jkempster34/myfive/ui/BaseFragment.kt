package com.jkempster34.myfive.ui

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    protected fun navigateToFragment(direction: NavDirections) {
        findNavController().navigate(direction)
    }
}