package com.example.propertymanagementapplication.models


data class RegisterUserResponse(
    val `data`: RegisterUserData?,
    val error: Boolean = true,
    val message: String
)

data class RegisterUserData(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val name: String,
    val password: String,
    val type: String
)