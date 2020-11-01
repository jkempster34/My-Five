package com.jkempster34.deathclockadvanced.di

import com.jkempster34.deathclockadvanced.data.firebase.AuthRepository
import com.jkempster34.deathclockadvanced.data.firebase.DefaultAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AuthenticationModule {

    @Binds
    abstract fun bindAuthRepository(
        defaultAuthRepository: DefaultAuthRepository
    ): AuthRepository
}