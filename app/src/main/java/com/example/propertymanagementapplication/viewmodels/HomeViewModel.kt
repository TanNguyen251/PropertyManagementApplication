package com.example.propertymanagementapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagementapplication.di.components.DaggerComponent
import com.example.propertymanagementapplication.di.modules.Module
import com.example.propertymanagementapplication.models.HomeItem
import javax.inject.Inject

class HomeViewModel: ViewModel() {
    val homeItemLiveData: MutableLiveData<ArrayList<HomeItem>> = MutableLiveData()
    @Inject
    lateinit var homeItemList: ArrayList<HomeItem>
    val inject = DaggerComponent.builder().module(Module()).build().inject(this)
    fun getHomeItemList(): MutableLiveData<ArrayList<HomeItem>>{
        homeItemLiveData.postValue(homeItemList)
        return homeItemLiveData
    }

}