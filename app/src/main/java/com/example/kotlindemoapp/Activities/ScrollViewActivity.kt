package com.example.kotlindemoapp.Activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_scroll_view.*

class ScrollViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_view)

        initToolbar()


        Utils.setImageToImageView(applicationContext, image1ImageView, R.drawable.cafe3)
        Utils.setImageToImageView(applicationContext, image2ImageView, R.drawable.cafe1)
        Utils.setImageToImageView(applicationContext, image3ImageView, R.drawable.cafe2)
        Utils.setImageToImageView(applicationContext, image4ImageView, R.drawable.cafe3)
        Utils.setImageToImageView(applicationContext, image5ImageView, R.drawable.cafe4)
        Utils.setImageToImageView(applicationContext, image6ImageView, R.drawable.cafe1)
        val longString = StringBuilder()

        // create long string
        for (i in 0..29) {
            longString.append(resources.getString(R.string.dummy_text1))
        }

        longTextView.text = longString.toString()

    }
    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Horizontal ScrollView"

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