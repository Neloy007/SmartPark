package com.example.smartpark.di

import com.example.smartpark.feature_admin.data.repository.AdminRepositoryImpl
import com.example.smartpark.feature_admin.domain.repository.AdminRepository
import com.example.smartpark.feature_auth.data.repository.AuthRepositoryImpl
import com.example.smartpark.feature_auth.domain.repository.AuthRepository
import com.example.smartpark.feature_home.data.repository.ParkingRepositoryImpl
import com.example.smartpark.feature_home.domain.repository.ParkingRepository
import com.example.smartpark.feature_map.data.repository.MapRepositoryImpl
import com.example.smartpark.feature_map.domain.repository.MapRepository
import com.example.smartpark.feature_profile.data.repository.ProfileRepositoryImpl
import com.example.smartpark.feature_profile.domain.repository.ProfileRepository
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

    @Binds
    @Singleton
    abstract fun bindParkingRepository(
        impl: ParkingRepositoryImpl
    ): ParkingRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        impl: ProfileRepositoryImpl
    ): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindMapRepository(
        impl: MapRepositoryImpl
    ): MapRepository

    @Binds
    @Singleton
    abstract fun bindAdminRepository(
        impl: AdminRepositoryImpl
    ): AdminRepository
}