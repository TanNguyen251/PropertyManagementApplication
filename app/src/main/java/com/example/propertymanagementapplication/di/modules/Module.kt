package com.example.propertymanagementapplication.di.modules

import com.example.propertymanagementapplication.R
import com.example.propertymanagementapplication.models.HomeItem
import com.example.propertymanagementapplication.repository.PropertyService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class Module {
    @Singleton
    @Provides
    fun provideRetrofitClient(): PropertyService{
        return Retrofit.Builder()
            .baseUrl("https://apolis-property-management.herokuapp.com/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PropertyService::class.java)
    }
    @Singleton
    @Provides
    fun provideRetrofit():  Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://apolis-property-management.herokuapp.com/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHomeItemList(): ArrayList<HomeItem>{
        val homeItemList: ArrayList<HomeItem> = ArrayList()
        homeItemList.add(HomeItem("Alert", R.drawable.ic_baseline_notifications_24))
        homeItemList.add(HomeItem("Properties", R.drawable.ic_baseline_house_24))
        homeItemList.add(HomeItem("Tenants", R.drawable.ic_baseline_person_24))
        homeItemList.add(HomeItem("Transactions", R.drawable.ic_baseline_credit_card_24))
        homeItemList.add(HomeItem("Collect Rent", R.drawable.ic_baseline_monetization_on_24))
        homeItemList.add(HomeItem("To-Do List", R.drawable.ic_baseline_check_24))
        homeItemList.add(HomeItem("Trips", R.drawable.ic_baseline_directions_car_24))
        homeItemList.add(HomeItem("Documents", R.drawable.ic_baseline_insert_drive_file_24))
        homeItemList.add(HomeItem("Vendors", R.drawable.ic_baseline_people_24))
        return homeItemList
    }
}