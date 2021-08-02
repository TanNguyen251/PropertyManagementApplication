package com.example.propertymanagementapplication.repository

import com.example.propertymanagementapplication.models.Property

class PropertyRepository(val propertyService: PropertyService) {
    suspend fun addProperty(property: Property) = propertyService.addProperty(property)
}