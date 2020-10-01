package com.example.kotlindemoapp.Activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.R
import com.google.android.material.navigation.NavigationView

class NavigationDrawerActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    //Feel free to share your thoughts or feedback. Happy Kotlin…

    internal lateinit var toolbar: Toolbar
    internal lateinit var drawerLinearLayout: LinearLayout
    internal lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        initUI()

    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_subject, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_menu_2) {
            val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
            if (drawer.isDrawerOpen(GravityCompat.END)) {
                drawer.closeDrawer(GravityCompat.END)
            } else {
                drawer.openDrawer(GravityCompat.END)
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_home) {
            Toast.makeText(this, "Clicked Home.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_category) {
            Toast.makeText(this, "Clicked Category.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_feature) {
            Toast.makeText(this, "Clicked Feature.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_popular) {
            Toast.makeText(this, "Clicked Popular.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_recent) {
            Toast.makeText(this, "Clicked Recent.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_search) {
            Toast.makeText(this, "Clicked Search.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_profile) {
            Toast.makeText(this, "Clicked Profile.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_favourite) {
            Toast.makeText(this, "Clicked Favourite.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, "Clicked Logout.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_about_us) {
            Toast.makeText(this, "Clicked About Us.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_contact_us) {
            Toast.makeText(this, "Clicked Contact Us.", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.nav_setting) {
            Toast.makeText(this, "Clicked Setting.", Toast.LENGTH_SHORT).show()
        }

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true

    }


    fun onMenuClicked(view: View) {
        resetMenuColor()

        selectMenu(view)

        drawer.closeDrawer(GravityCompat.START)
    }

    private fun selectMenu(view: View) {
        try {
            val imageView = view as ImageView
            if (imageView.id == R.id.homeImageView) {
                Toast.makeText(this, "Clicked Home.", Toast.LENGTH_SHORT).show()
            } else if (imageView.id == R.id.announceImageView) {
                Toast.makeText(this, "Clicked Announce.", Toast.LENGTH_SHORT).show()
            } else if (imageView.id == R.id.profileImageView) {
                Toast.makeText(this, "Clicked Graph.", Toast.LENGTH_SHORT).show()
            } else if (imageView.id == R.id.clockImageView) {
                Toast.makeText(this, "Clicked History.", Toast.LENGTH_SHORT).show()
            } else if (imageView.id == R.id.profileImageView) {
                Toast.makeText(this, "Clicked Profile.", Toast.LENGTH_SHORT).show()
            } else if (imageView.id == R.id.infoImageView) {
                Toast.makeText(this, "Clicked Info.", Toast.LENGTH_SHORT).show()
            } else if (imageView.id == R.id.settingImageView) {
                Toast.makeText(this, "Clicked Setting.", Toast.LENGTH_SHORT).show()
            } else if (imageView.id == R.id.powerImageView) {
                Toast.makeText(this, "Clicked Power.", Toast.LENGTH_SHORT).show()
            }
            imageView.setColorFilter(
                ContextCompat.getColor(this, R.color.md_white_1000),
                PorterDuff.Mode.SRC_ATOP
            )
            imageView.setBackgroundColor(ContextCompat.getColor(this, R.color.md_grey_700))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun resetMenuColor() {

        try {
            val color = ContextCompat.getColor(this, R.color.md_grey_600)

            for (index in 0 until drawerLinearLayout.childCount) {
                val nextChild = drawerLinearLayout.getChildAt(index)

                if (nextChild is ImageView) {
                    nextChild.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                    nextChild.setBackground(null)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun initUI() {
        initToolbar()

        drawer = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        if (Utils.isRTL) {
            navigationView.textDirection = View.TEXT_DIRECTION_RTL
        } else {
            navigationView.textDirection = View.TEXT_DIRECTION_LTR
        }

        val headerLayout = navigationView.getHeaderView(0)
        val userImageView = headerLayout.findViewById<ImageView>(R.id.userImageView)
        Utils.setCircleImageToImageView(this, userImageView, R.drawable.banner, 0, 0)

        val rightNavigationView = findViewById<NavigationView>(R.id.nav_view_right)
        rightNavigationView.setNavigationItemSelectedListener(this)
        if (Utils.isRTL) {
            rightNavigationView.textDirection = View.TEXT_DIRECTION_RTL
        } else {
            rightNavigationView.textDirection = View.TEXT_DIRECTION_LTR
        }

        val rightHeaderLayout = rightNavigationView.getHeaderView(0)

        drawerLinearLayout = rightHeaderLayout.findViewById(R.id.drawerLinearLayout)
    }

    private fun initToolbar() {

        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.md_white_1000
                ), PorterDuff.Mode.SRC_ATOP
            )
        }

        toolbar.title = "Menu 4"

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
}