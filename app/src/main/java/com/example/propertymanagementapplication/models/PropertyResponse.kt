package com.example.propertymanagementapplication.models

data class PropertyResponse(
    val `data`: PropertyData,
    val error: Boolean,
    val message: String
)

data class PropertyData(
    val __v: Int,
    val _id: String,
    val address: String,
    val city: String,
    val country: String,
    val image: String,
    val mortageInfo: Boolean,
    val propertyStatus: Boolean,
    val purchasePrice: String,
    val state: String,
    val userId: String,
    val userType: String
)