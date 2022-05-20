package com.example.loginapp.repository.authentication

import com.example.loginapp.data.network.response.LoginResponse

interface AuthenticationRepository {
    suspend fun authenticate(userName: String, password: String) : LoginResponse
}