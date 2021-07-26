package com.example.propertymanagementapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.propertymanagementapplication.di.components.DaggerComponent
import com.example.propertymanagementapplication.di.modules.Module
import com.example.propertymanagementapplication.models.LoginUser
import com.example.propertymanagementapplication.models.LoginUserResponse
import com.example.propertymanagementapplication.repository.PropertyService
import com.example.propertymanagementapplication.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

class LoginViewModel: ViewModel() {

    val loginUserLiveData: MutableLiveData<LoginUserResponse> = MutableLiveData()

    @Inject
    lateinit var propertyService: PropertyService

    val inject = DaggerComponent.builder().module(Module()).build().inject(this)

    fun loginUser(loginUser:LoginUser): MutableLiveData<LoginUserResponse>{
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = UserRepository(propertyService).loginUser(loginUser)
                if(response.isSuccessful){
                    loginUserLiveData.postValue(response.body())
                } else {
                    val errorResponseJson = JSONObject(response.errorBody()?.string())
                    Log.d("abc", "Login error: $errorResponseJson")
                    loginUserLiveData.postValue(LoginUserResponse(null, null, errorResponseJson.getBoolean("error"), errorResponseJson.getString("message")))
                }
            } catch (e:Exception) {
                loginUserLiveData.postValue(LoginUserResponse(null, null, true, e.message?:"Unable to login"))
            }
        }
        return loginUserLiveData
    }
}