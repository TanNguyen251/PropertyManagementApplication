package com.example.propertymanagementapplication.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagementapplication.R
import com.example.propertymanagementapplication.models.User
import com.example.propertymanagementapplication.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener,
    AdapterView.OnItemSelectedListener {
    private lateinit var pd: ProgressDialog
    private val registerViewModel: RegisterViewModel by viewModels()
    private val userType = arrayOf("tenant","landlord")
    private lateinit var type: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupObserver()
        init()
    }

    private fun init(){
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userType)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        register_spinner_user_type.adapter = arrayAdapter
        register_spinner_user_type.onItemSelectedListener = this
        pd = ProgressDialog(this)
        pd.setCancelable(true)
        register_button_register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            register_button_register -> {
                val name = register_et_name.text.toString()
                val email = register_et_email.text.toString()
                val password = register_et_password.text.toString()
                val user = User(name, email, password, type)
                pd.setMessage("Registering...")
                pd.show()
                /*registerViewModel.registerUser(user).observe(this){
                    if (it.error){
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()
                    }
                    pd.dismiss()
                }*/
                registerViewModel.registerUserCoroutine(user)
            }
        }
    }

    fun setupObserver(){
        registerViewModel.userLiveData.observe(this){
            if (it.error){
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()
            }
            pd.dismiss()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        type = userType[position]
        if(type == "tenant"){
            register_et_landlord_email.visibility = View.VISIBLE
        } else {
            register_et_landlord_email.visibility = View.INVISIBLE
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}