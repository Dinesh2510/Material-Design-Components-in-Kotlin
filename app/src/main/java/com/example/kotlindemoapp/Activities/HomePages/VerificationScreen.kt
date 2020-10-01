package com.example.kotlindemoapp.Activities.HomePages

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_verification_screen.*

class VerificationScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_screen)
        initUI()

        initActions()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initUI() {
        initToolbar()

        val id = Utils.getDrawableInt(applicationContext, "verification1")
        Utils.setImageToImageView(applicationContext, bgImageView, id)

    }

    private fun initActions() {
        continueButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Clicked Continue.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.md_white_1000
                ), PorterDuff.Mode.SRC_ATOP
            )
        }

        toolbar.title = "Verification 1"

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_white_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar)
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set support action bar.")
        }

        try {
            if (supportActionBar != null) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set display home as up enabled.")
        }

    }}