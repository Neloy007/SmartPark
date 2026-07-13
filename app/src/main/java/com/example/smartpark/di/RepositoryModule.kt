package com.example.smartpark.di

import com.example.smartpark.feature_auth.data.repository.AuthRepositoryImpl
import com.example.smartpark.feature_auth.domain.repository.AuthRepository
import com.example.smartpark.feature_user.data.repository.UserRepositoryImpl
import com.example.smartpark.feature_user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}