package com.example.propertymanagementapplication.di.components

import com.example.propertymanagementapplication.activities.HomeActivity
import com.example.propertymanagementapplication.di.modules.Module
import com.example.propertymanagementapplication.viewmodels.HomeViewModel
import com.example.propertymanagementapplication.viewmodels.LoginViewModel
import com.example.propertymanagementapplication.viewmodels.RegisterViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(Module::class))
interface Component {
    fun inject(loginViewModel: LoginViewModel)
    fun inject(registerViewModel: RegisterViewModel)
    fun inject(homeActivity: HomeActivity)
    fun inject(homeViewModel: HomeViewModel)
}