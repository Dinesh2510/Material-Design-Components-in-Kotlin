package com.example.kotlindemoapp.Activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_floating_btn.*

class FloatingBtn : AppCompatActivity() {
    private var twist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_btn)

        initToolbar()

        Utils.hideFirstFab(linearVideo)
        Utils.hideFirstFab(linearCamera)
        Utils.hideFirstFab(linearPhoto)

        fab.setOnClickListener { v ->

            twist = Utils.twistFab(v, !twist)

            if (twist) {

                Utils.showFab(linearVideo)
                Utils.showFab(linearCamera)
                Utils.showFab(linearPhoto)

            } else {

                Utils.hideFab(linearVideo)
                Utils.hideFab(linearCamera)
                Utils.hideFab(linearPhoto)

            }
        }

        fab_video.setOnClickListener { Toast.makeText(applicationContext, "Opne Video clicked", Toast.LENGTH_SHORT).show() }

        fab_camera.setOnClickListener { Toast.makeText(applicationContext, "Open Camera clicked", Toast.LENGTH_SHORT).show() }

        fab_photo.setOnClickListener { Toast.makeText(applicationContext, "View Photos clicked", Toast.LENGTH_SHORT).show() }

    }
    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "SubMenu FAB with Text"

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
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

}