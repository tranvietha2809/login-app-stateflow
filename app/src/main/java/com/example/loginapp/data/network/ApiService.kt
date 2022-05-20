package com.example.loginapp.data.network

import com.example.loginapp.data.network.request.LoginRequest
import com.example.loginapp.data.network.response.LoginResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {
    companion object{
        operator fun invoke(): ApiService {
            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder().client(okHttpClient).baseUrl("http:///10.0.2.2:3001/v1/")
                .addConverterFactory(Json.asConverterFactory(contentType))
                .build()
                .create(ApiService::class.java)
        }
    }

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest) : LoginResponse
}