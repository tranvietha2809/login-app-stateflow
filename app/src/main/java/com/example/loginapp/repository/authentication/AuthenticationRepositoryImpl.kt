package com.example.loginapp.repository.authentication

import com.example.loginapp.data.network.ApiService
import com.example.loginapp.data.network.request.LoginRequest
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(val apiService: ApiService) :
    AuthenticationRepository {
    override suspend fun authenticate(userName: String, password: String) = apiService.login(
        LoginRequest(userName, password)
    )
}
