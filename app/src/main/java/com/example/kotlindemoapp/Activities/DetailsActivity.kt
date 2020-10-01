package com.example.kotlindemoapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.kotlindemoapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.ui_bottom_sheet_basic_bottom_sheet_list.*

class DetailsActivity : AppCompatActivity() {
    internal lateinit var dialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val str_title = intent.extras?.getString("title1")
        val str_title2 = intent.extras?.getString("title2")

        val title: TextView = findViewById(R.id.title)
        val title2: TextView = findViewById(R.id.title2)

        title.text = str_title
        title2.text = str_title2

        btn_btm_sheet.setOnClickListener { MyCustomerBottomSheetDialog() }


    }

    fun MyCustomerBottomSheetDialog() {
        dialog = BottomSheetDialog(this)

        dialog.setContentView(R.layout.ui_bottom_sheet_basic_bottom_sheet_list)

        val shareTextView = dialog.shareUiBottomSheet
        shareTextView.setOnClickListener {
            Toast.makeText(
                this,
                "Clicked Shared.",
                Toast.LENGTH_SHORT
            ).show()
        }

        val getLinkTextView = dialog.getLinkUiBottomSheet
        getLinkTextView.setOnClickListener {
            Toast.makeText(
                this,
                "Clicked Get Link.",
                Toast.LENGTH_SHORT
            ).show()
        }
        dialog.show()

        val downloadTextView = dialog.downloadUiBottomSheet
        downloadTextView.setOnClickListener {
            Toast.makeText(
                this,
                "Clicked Download.",
                Toast.LENGTH_SHORT
            ).show()
        }
        dialog.show()

        val viewDetailTextView = dialog.viewDetailUiBottomSheet
        viewDetailTextView.setOnClickListener {
            Toast.makeText(
                this,
                "Clicked View Detail.",
                Toast.LENGTH_SHORT
            ).show()
        }
        dialog.show()
    }

}