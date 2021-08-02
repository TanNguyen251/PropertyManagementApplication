package com.example.propertymanagementapplication.models

import com.google.gson.annotations.SerializedName

data class Property(
    @SerializedName("address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("country") val country: String,
    @SerializedName("zipcode") val zipcode: Int,
    @SerializedName("image") val image: String,
    @SerializedName("mortageInfo") val mortageInfo: Boolean?,
    @SerializedName("propertyStatus") val propertyStatus: Boolean?,
    @SerializedName("purchasePrice") val purchasePrice: String,
    @SerializedName("state") val state: String,
    @SerializedName("userId") val userId: String?,
    @SerializedName("userType") val userType: String?
)
