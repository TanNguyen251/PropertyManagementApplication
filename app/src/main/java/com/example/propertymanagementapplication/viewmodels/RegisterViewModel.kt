package com.example.propertymanagementapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.propertymanagementapplication.di.components.DaggerComponent
import com.example.propertymanagementapplication.di.modules.Module
import com.example.propertymanagementapplication.models.LoginUser
import com.example.propertymanagementapplication.models.LoginUserResponse
import com.example.propertymanagementapplication.models.RegisterUserResponse
import com.example.propertymanagementapplication.models.User
import com.example.propertymanagementapplication.repository.PropertyService
import com.example.propertymanagementapplication.repository.RetrofitProperty
import com.example.propertymanagementapplication.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

class RegisterViewModel: ViewModel() {
    val userLiveData: MutableLiveData<RegisterUserResponse> = MutableLiveData()

    @Inject
    lateinit var propertyService: PropertyService
    fun registerUser(user: User) = UserRepository(propertyService).postUser(user)

    val inject = DaggerComponent.builder().module(Module()).build().inject(this)


    fun registerUserCoroutine(user:User): MutableLiveData<RegisterUserResponse>{
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = UserRepository(propertyService).postUserCoroutine(user)
                if(response.isSuccessful){
                    userLiveData.postValue(response.body())
                } else {
                    val errorResponseJson = JSONObject(response.errorBody()?.string())
                    Log.d("abc", errorResponseJson.toString())
                    userLiveData.postValue(RegisterUserResponse(null, errorResponseJson.getBoolean("error"), errorResponseJson.getString("message")))
                }
            } catch (e: Exception){
                userLiveData.postValue(RegisterUserResponse(null, true, e.message?:"Unable to register"))
            }
        }
        return userLiveData
    }


}