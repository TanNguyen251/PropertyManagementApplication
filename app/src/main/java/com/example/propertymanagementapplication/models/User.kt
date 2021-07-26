package com.example.propertymanagementapplication.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") var name: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("password") var password: String?,
    @SerializedName("type") var type: String?
)
