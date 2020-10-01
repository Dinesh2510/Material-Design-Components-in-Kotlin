package com.example.kotlindemoapp.Activities.HomePages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initDataBindings()

        initActions()

    }


    private fun initDataBindings() {
        val id = R.drawable.login_background
        Utils.setImageToImageView(applicationContext, bgImageView, id)
    }

    private fun initActions() {
        forgotButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Clicked Forgot Password",
                Toast.LENGTH_SHORT
            ).show()
        }

        createTextView.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        facebookLinearLayout.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Clicked Facebook",
                Toast.LENGTH_SHORT
            ).show()
        }

        twitterLinearLayout.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Clicked Twitter",
                Toast.LENGTH_SHORT
            ).show()
        }

        googlePlusLinearLayout.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Clicked Google Plus",
                Toast.LENGTH_SHORT
            ).show()
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

    }
}