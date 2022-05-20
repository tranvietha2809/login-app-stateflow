package com.example.loginapp.data.network.response

import com.example.loginapp.data.entities.User
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(val user: User)