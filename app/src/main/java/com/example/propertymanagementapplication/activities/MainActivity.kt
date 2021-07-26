package com.example.propertymanagementapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.propertymanagementapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init(){

        main_button_register.setOnClickListener(this)
        main_button_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            main_button_register ->{
                startActivity(Intent(this, RegisterActivity::class.java))
            }

            main_button_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}