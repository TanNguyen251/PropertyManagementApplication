package com.example.propertymanagementapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagementapplication.databinding.ActivityPropertiesBinding

class PropertiesActivity : AppCompatActivity() {
    lateinit var binding: ActivityPropertiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}