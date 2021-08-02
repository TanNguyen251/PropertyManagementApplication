package com.example.propertymanagementapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.propertymanagementapplication.di.components.DaggerComponent
import com.example.propertymanagementapplication.di.modules.Module
import com.example.propertymanagementapplication.models.Property
import com.example.propertymanagementapplication.models.PropertyResponse
import com.example.propertymanagementapplication.repository.PropertyRepository
import com.example.propertymanagementapplication.repository.PropertyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PropertyViewModel: ViewModel() {
    val propertyLiveData: MutableLiveData<PropertyResponse> = MutableLiveData()

    @Inject
    lateinit var propertyService: PropertyService

    val inject = DaggerComponent.builder().module(Module()).build().inject(this)

    fun addProperty(property: Property){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = PropertyRepository(propertyService).addProperty(property)
                if (response.isSuccessful){
                    propertyLiveData.postValue(response.body())
                } else {

                }
            } catch (e: Exception){

            }
        }
    }
}