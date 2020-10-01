package com.example.kotlindemoapp.Activities

import android.app.Dialog
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_alert_dialog.*

class AlertDialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_dialog)
        initToolbar()

        basicButton.setOnClickListener { getBasicDialog() }
        fullscreenButton.setOnClickListener { getFullScreenDialog() }
        customPrimaryButton.setOnClickListener { getCustomStyleDialog(R.style.AlertDialogPrimary) }
        customAccentButton.setOnClickListener { getCustomStyleDialog(R.style.AlertDialogAccent) }
        customLayoutPrimaryButton.setOnClickListener { getCustomLayoutDialog(R.layout.dialog_alert_custom_layout, R.color.colorPrimary) }
        customLayoutAccentButton.setOnClickListener { getCustomLayoutDialog(R.layout.dialog_alert_custom_layout, R.color.colorAccent) }
    }
    private fun getBasicDialog() {
        AlertDialog.Builder(this)
            .setTitle("Warning!")
            .setMessage("Your storage is almost full.")
            .setNegativeButton("OK") { dialog, _ ->

                dialog.cancel()
                Toast.makeText(applicationContext, "Clicked Basic Dialog Button.", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Go to setting") { dialog, _ ->

                dialog.cancel()
                Toast.makeText(applicationContext, "Clicked 'Go to Setting'.", Toast.LENGTH_SHORT).show()
            }
            .setIcon(R.drawable.baseline_warning_black_24)
            .show()
    }

    private fun getFullScreenDialog() {
        AlertDialog.Builder(this, R.style.AlertDialogFullScreen) //android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
            .setTitle("Warning!")
            .setMessage("Your storage is almost full.")
            .setNegativeButton("OK") { dialog, _ ->

                dialog.cancel()
                Toast.makeText(applicationContext, "Clicked Fullscreen Dialog Button.", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Go to setting") { dialog, _ ->

                dialog.cancel()
                Toast.makeText(applicationContext, "Clicked 'Go to Setting' Button.", Toast.LENGTH_SHORT).show()
            }
            .setIcon(R.drawable.baseline_warning_black_24)
            .show()
    }

    private fun getCustomStyleDialog(styleId: Int) {
        AlertDialog.Builder(ContextThemeWrapper(this, styleId))
            .setTitle("Warning!")
            .setMessage("Your storage is almost full.")
            .setNegativeButton("OK") { dialog, _ ->

                dialog.cancel()
                Toast.makeText(applicationContext, "Clicked Custom Style Dialog Button.", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Go to setting") { dialog, _ ->

                dialog.cancel()
                Toast.makeText(applicationContext, "Clicked 'Go to Setting' Button.", Toast.LENGTH_SHORT).show()
            }
            .setIcon(R.drawable.baseline_warning_white_24)
            .show()
    }

    private fun getCustomLayoutDialog(layoutId: Int, colorId: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)

        val linearLayout = dialog.findViewById<LinearLayout>(R.id.customAlertTitleLayout)
        linearLayout.setBackgroundColor(ContextCompat.getColor(this,colorId))

        val image = dialog.findViewById<ImageView>(R.id.customAlertIconImageView)
        image.setImageResource(R.drawable.baseline_warning_white_24)

        val titleTextView = dialog.findViewById<TextView>(R.id.customAlertTitleTextView)
        titleTextView.text = "Warning!"


        val msgTextView = dialog.findViewById<TextView>(R.id.customAlertMessageTextView)
        msgTextView.text = "Your storage is almost full."

        val positiveButton = dialog.findViewById<Button>(R.id.customAlertPositiveButton)
        positiveButton.text = "Go to setting"
        positiveButton.setTextColor(ContextCompat.getColor(this,colorId))

        positiveButton.setOnClickListener {

            dialog.cancel()
            Toast.makeText(applicationContext, "Clicked 'Go to Setting' Button.", Toast.LENGTH_SHORT).show()
        }

        val negativeButton = dialog.findViewById<Button>(R.id.customAlertNegativeButton)
        negativeButton.text = "OK"
        negativeButton.setTextColor(ContextCompat.getColor(this,colorId))

        negativeButton.setOnClickListener {

            dialog.cancel()
            Toast.makeText(applicationContext, "Clicked 'OK' Button.", Toast.LENGTH_SHORT).show()
        }

        if (dialog.window != null) {
            dialog.window?.attributes = getLayoutParams(dialog)
        }
        dialog.show()
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

    //region Init Toolbar

    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Alert Dialog"

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