package com.example.propertymanagementapplication.repository

import com.example.propertymanagementapplication.models.LoginUser
import com.example.propertymanagementapplication.models.LoginUserResponse
import com.example.propertymanagementapplication.models.RegisterUserResponse
import com.example.propertymanagementapplication.models.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PropertyService {
    @POST("auth/register")
    fun registerUser(@Body user: User): Single<RegisterUserResponse>
    @POST("auth/register")
    suspend fun registerUserCoroutine(@Body user: User): Response<RegisterUserResponse>
    @POST("auth/login")
    suspend fun loginUser(@Body loginUser: LoginUser): Response<LoginUserResponse>
}