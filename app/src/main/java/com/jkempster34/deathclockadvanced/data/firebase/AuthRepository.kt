package com.jkempster34.deathclockadvanced.data.firebase

import com.jkempster34.deathclockadvanced.data.User

interface AuthRepository {

    fun getCurrentUser(): User?
}