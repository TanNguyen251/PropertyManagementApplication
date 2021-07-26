package com.example.propertymanagementapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.propertymanagementapplication.databinding.ActivityTenantBinding

class TenantActivity : AppCompatActivity() {
    lateinit var binding: ActivityTenantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenantBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}