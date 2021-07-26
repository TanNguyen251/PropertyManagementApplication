package com.example.propertymanagementapplication.models

data class LoginUserResponse(
    val token: String?,
    val user: LoginUserData?,
    val error: Boolean?,
    val message: String?,
)

data class LoginUserData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val name: String,
    val password: String,
    val type: String
)