package com.example.propertymanagementapplication.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapplication.databinding.ActivityPropertiesBinding
import com.example.propertymanagementapplication.models.Property
import com.example.propertymanagementapplication.viewmodels.PropertyViewModel

class PropertiesActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener,
    View.OnClickListener {
    lateinit var binding: ActivityPropertiesBinding
    var propertyStatus: Boolean? = null
    var mortgageInfo: Boolean? = null
    lateinit var propertyViewModel: PropertyViewModel
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setupViewModel()
        setupObserver()
    }

    private fun init(){
        binding.propertiesCbMortgage.setOnCheckedChangeListener(this)
        binding.propertiesBtnSave.setOnClickListener(this)
    }

    fun setupViewModel(){
        propertyViewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)
    }

    fun setupObserver(){
        propertyViewModel.propertyLiveData.observe(this){
            if(it.message == "property added successfully"){
                startActivity(Intent(this, TenantActivity::class.java))
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when(buttonView){
            binding.propertiesCbMortgage -> {
                mortgageInfo = isChecked
            }

            binding.propertiesCbMultiple -> {

            }
        }
    }

    override fun onClick(v: View?) {
        when (v){
            binding.propertiesBtnSave -> {
                val address = binding.propertiesEtAddress.text.toString()
                val city = binding.propertiesEtCity.text.toString()
                val zipcode = binding.propertiesEtZip.text.toString().toInt()
                val country = binding.propertiesEtCountry.text.toString()
                val state = binding.propertiesEtState.text.toString()
                val purchasePrice = binding.propertiesEtPurchase.text.toString()
                sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)
                val userId = sharedPreferences.getString("USERID", "")
                val userType = sharedPreferences.getString("USERTYPE","")
                propertyStatus = binding.propertiesEtStatus.text.toString() == "owned"
                val property = Property(address,city,country,zipcode,"",mortgageInfo,propertyStatus,purchasePrice,state, userId, userType)
                propertyViewModel.addProperty(property)
            }
        }
    }
}