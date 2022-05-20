package com.example.loginapp.di

import com.example.loginapp.data.network.ApiService
import com.example.loginapp.repository.authentication.AuthenticationRepository
import com.example.loginapp.repository.authentication.AuthenticationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesApiService() : ApiService = ApiService.invoke()

    @Provides
    @Singleton
    fun providesAuthenticationRepository(apiService: ApiService) : AuthenticationRepository =
        AuthenticationRepositoryImpl(apiService)
}