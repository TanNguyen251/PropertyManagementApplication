package com.example.propertymanagementapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.propertymanagementapplication.adapters.HomeItemAdapter
import com.example.propertymanagementapplication.databinding.ActivityHomeBinding
import com.example.propertymanagementapplication.viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupObserver()
    }

    fun setupViewModel(){
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    fun setupObserver(){
        homeViewModel.getHomeItemList().observe(this){
            val homeItemAdapter = HomeItemAdapter(it)
            binding.homeRvHome.adapter = homeItemAdapter
            binding.homeRvHome.layoutManager = GridLayoutManager(this, 3)
            homeItemAdapter.onItemClickListener = { homeItem ->
                when (homeItem.name) {
                    "Properties" -> {
                        startActivity(Intent(this, PropertiesActivity::class.java))
                    }
                    "Tenants" -> {
                        startActivity(Intent(this, TenantActivity::class.java))
                    }
                }
            }
        }
    }
}