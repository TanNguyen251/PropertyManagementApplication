package com.example.propertymanagementapplication.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapplication.models.LoginUser

class LoginViewModelFactory(var params: LoginUser) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginUser::class.java).newInstance(params)
    }

}