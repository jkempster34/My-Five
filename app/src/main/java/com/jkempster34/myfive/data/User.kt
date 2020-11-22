package com.jkempster34.myfive.data

import java.io.Serializable

data class User(
    val uid: String,
    val name: String?,
    val email: String?
) : Serializable