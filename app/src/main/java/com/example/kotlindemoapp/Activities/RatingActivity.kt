package com.example.kotlindemoapp.Activities

import android.app.Dialog
import android.graphics.PorterDuff
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_rating.*
import kotlinx.android.synthetic.main.ui_rating_popup_rating_layout.*

class RatingActivity : AppCompatActivity() {
    internal lateinit var dialog: Dialog
    internal lateinit var submitButton: Button
    internal lateinit var cancelButton: Button
    internal lateinit var ratingTextView: TextView
    internal lateinit var ratingBar2: RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        initUI()
        initActions()
        ratingBar2.setOnRatingBarChangeListener { _, rating, _ ->
            ratingTextView.text = rating.toString()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initUI() {
        initToolbar()
        getCustomLayoutDialog(R.layout.ui_rating_popup_rating_layout)

    }

    private fun getCustomLayoutDialog(layoutId: Int) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        submitButton = dialog.findViewById(R.id.submitButton)
        ratingTextView = dialog.findViewById(R.id.ratingTextView)
        ratingBar2 = dialog.findViewById(R.id.ratingBar2)

        cancelButton = dialog.findViewById(R.id.cancelButton)

        if (dialog.window != null) {
            dialog.window?.attributes = getLayoutParams(dialog)
        }

        dialog.show()
    }

    private fun initActions() {

        submitButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Clicked Submit.",
                Toast.LENGTH_SHORT
            ).show()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Clicked Cancel.",
                Toast.LENGTH_SHORT
            ).show()
        }

        badgeImageView.setOnClickListener { getCustomLayoutDialog(R.layout.ui_rating_popup_rating_layout) }

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

        toolbar.title = "Popup Rating"

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

    private fun getLayoutParams(dialog: Dialog): WindowManager.LayoutParams {
        val layoutParams = WindowManager.LayoutParams()
        if (dialog.window != null) {
            layoutParams.copyFrom(dialog.window?.attributes)
        }
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT

        return layoutParams
    }
}