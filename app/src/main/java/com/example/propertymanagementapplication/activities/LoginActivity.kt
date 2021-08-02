package com.example.propertymanagementapplication.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapplication.databinding.ActivityLoginBinding
import com.example.propertymanagementapplication.models.LoginUser
import com.example.propertymanagementapplication.viewmodels.LoginViewModel
import com.example.propertymanagementapplication.viewmodels.factories.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupObserver()
        init()
    }

    fun setupViewModel(){
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    fun init(){
        binding.loginButtonLogin.setOnClickListener{
            val email = binding.loginEtEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            val loginUser = LoginUser(email, password)
            loginViewModel.loginUser(loginUser)
        }
    }
    fun setupObserver(){
        loginViewModel.loginUserLiveData.observe(this) {
            if(it.token != null){
                sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("USERID", it.user?._id)
                editor.putString("USERTYPE", it.user?.type)
                editor.commit()
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
            /*if(it.error == true){
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            } else if (it.error == null){
                startActivity(Intent(this, HomeActivity::class.java))
            }*/
        }
    }
}