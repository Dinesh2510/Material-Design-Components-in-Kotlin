package com.example.kotlindemoapp

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.Activities.*
import com.example.kotlindemoapp.Activities.DateTimePicker
import com.example.kotlindemoapp.Activities.Forms.LoginActivity
import com.example.kotlindemoapp.Activities.HomePages.HomePage
import com.example.kotlindemoapp.Activities.HomePages.Login
import com.example.kotlindemoapp.Activities.RecyclerViews.GridRecyclerViewWithThreeCol
import com.example.kotlindemoapp.Activities.RecyclerViews.GridRecyclerWithTwoCol
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        val btn_dialog_1 = findViewById<Button>(R.id.btn_dialog_1)
        val btn_dialog_2 = findViewById<Button>(R.id.btn_dialog_2)
        val btn_dialog_3 = findViewById<Button>(R.id.btn_dialog_3)
        val btn_dialog_4 = findViewById<Button>(R.id.btn_dialog_4)
        val btn_dialog_5 = findViewById<Button>(R.id.btn_dialog_5)
        val btn_dialog_6 = findViewById<Button>(R.id.btn_dialog_6)

        btn_dialog_1.setOnClickListener {
            val intent = Intent(this, CustomColorBottomNavigation::class.java)
            startActivity(intent)
        }
        btn_dialog_2.setOnClickListener {
            val intent = Intent(this, TabLayoutWithIcon::class.java)
            startActivity(intent)
        }
        btn_dialog_3.setOnClickListener {
            val intent = Intent(this, GridRecyclerWithTwoCol::class.java)
            startActivity(intent)
        }
        btn_dialog_4.setOnClickListener {
            val intent = Intent(this, NavigationDrawerActivity::class.java)
            startActivity(intent)
        }
        val value: Any = btn_dialog_5.setOnClickListener {
            val intent = Intent(this, WebviewActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_6.setOnClickListener {
            val intent = Intent(this, AutoCompleteEditviewActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_7.setOnClickListener {
            val intent = Intent(this, SpinnerActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_8.setOnClickListener {
            val intent = Intent(this, SearchViewActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_9.setOnClickListener {
            val intent = Intent(this, CalclulatorActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_10.setOnClickListener {
            val intent = Intent(this, ScrollViewActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_11.setOnClickListener {
            val intent = Intent(this, CustomRecyclerView::class.java)
            startActivity(intent)
        }
        btn_dialog_12.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_13.setOnClickListener {
            val intent = Intent(this, AlertDialog::class.java)
            startActivity(intent)
        }
        btn_dialog_14.setOnClickListener {
            val intent = Intent(this, DateTimePicker::class.java)
            startActivity(intent)
        }
        btn_dialog_15.setOnClickListener {
            val intent = Intent(this, ExpandebleActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_16.setOnClickListener {
            val intent = Intent(this, FullScreenAdActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_17.setOnClickListener {
            val intent = Intent(this, BottomNavigationHideOnScroll::class.java)
            startActivity(intent)
        }
        btn_dialog_18.setOnClickListener {
            val intent = Intent(this, FloatingBtn::class.java)
            startActivity(intent)
        }
        btn_dialog_19.setOnClickListener {
            val intent = Intent(this, MusicPlayerActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_20.setOnClickListener {
            val intent = Intent(this, VideoPlayerActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_21.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        btn_dialog_22.setOnClickListener {
            val intent = Intent(this, PostList::class.java)
            startActivity(intent)
        }
        btn_dialog_23.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btn_dialog_24.setOnClickListener {
            showPopup(btn_dialog_24)
        }
        btn_dialog_25 . setOnClickListener {
            val intent = Intent(this, GridRecyclerViewWithThreeCol::class.java)
            startActivity(intent)
        }

    }
    //https://android-kotlin-fun-mars-server.appspot.com/realestate

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

        toolbar.title = "Kotlin Demo App"

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(this, "Clicked " + item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showPopup(view: View) {
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.header_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.header1 -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.header2 -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
                R.id.header3 -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                }
            }

            true
        })

        popup.show()
    }

}
/*
<!-- TODO: Update  layout -->
1. Bottom Navigation
2.Tab Layout
3.Grid RecyclerView
3.Linear RecyclerView
4.Navigation Drawer (Both Side)
5.WebView
6.AutoComplete TextView
7.MultiAutoComplete TextView  (Eg. apple,bat,cat,dog)
8.Spinner
9.Bottom Sheet
10.SearchView (Toolbar SearchView and Basic SearchView)
11.Calculator App
12. Rating
13.Dialog Box
14.Date Time Picker
15.AdMob Full Screen Ad
16.Collapsed View
17. Horizontal ScrollView 
18. Bottom Navigation Hide On Scroll
19. Login, Register, Verification Screen, Home Page
20.Music & Video Player
21. RecyclerView Using Volly API

*/
