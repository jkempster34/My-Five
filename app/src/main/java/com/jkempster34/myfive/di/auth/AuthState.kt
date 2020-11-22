package com.jkempster34.myfive.di.auth

import com.jkempster34.myfive.data.auth.AuthStateLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AuthState {

    @Singleton
    @Provides
    fun providesAuthStateLiveData(): AuthStateLiveData {
        return AuthStateLiveData()
    }
}