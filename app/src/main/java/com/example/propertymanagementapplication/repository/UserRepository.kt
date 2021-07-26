package com.example.propertymanagementapplication.repository

import com.example.propertymanagementapplication.models.LoginUser
import com.example.propertymanagementapplication.models.User
import com.example.propertymanagementapplication.remotedatasrouce.RemoteData

class UserRepository(val propertyService: PropertyService) {
    fun postUser(user:User) = RemoteData().postUser(user)

    suspend fun postUserCoroutine(user: User) = propertyService.registerUserCoroutine(user)

    suspend fun loginUser(loginUser: LoginUser) = propertyService.loginUser(loginUser)
}