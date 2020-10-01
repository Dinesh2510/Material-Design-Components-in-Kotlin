package com.example.kotlindemoapp.Activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.kotlindemoapp.Fragments.Call
import com.example.kotlindemoapp.Fragments.Chat
import com.example.kotlindemoapp.Fragments.Contact
import com.example.kotlindemoapp.Fragments.More
import com.example.kotlindemoapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_custom_color_bottom_navigation.*

class CustomColorBottomNavigation : AppCompatActivity() {
    private var content: FrameLayout? = null
    val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.chats -> {
                    toolbar.title = "Home"
                    val fragment = Chat()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.calls -> {
                    toolbar.title = "Call"
                    val fragment = Call()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.contacts -> {
                    toolbar.title = "Contact"
                    val fragment = Contact()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.more -> {
                    toolbar.title = "More"
                    val fragment = More()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_color_bottom_navigation)
        initToolbar()
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = Chat()
        addFragment(fragment)


    }


    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.md_white_1000
                ), PorterDuff.Mode.SRC_ATOP
            )
        }

        //toolbar.title = "Custom Color"

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

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.content_frame, fragment)
            .commit()
    }

/*    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_frame, fragment)
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
        //transaction.addToBackStack(null)
        transaction.commit()
    }*/
}