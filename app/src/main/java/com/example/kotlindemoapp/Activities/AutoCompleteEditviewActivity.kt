package com.example.kotlindemoapp.Activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_auto_complete_editview.*

class AutoCompleteEditviewActivity : AppCompatActivity() {
    internal lateinit var fruits: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_editview)


        initToolbar()

        fruits = resources.getStringArray(R.array.fruits)

        setDataToAutoCompleteTextView()

        setDataToMultiAutoCompleteTextView()

        setCheckedTextView()
    }

    private fun setDataToAutoCompleteTextView() {
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, fruits) //here we have add the fruits list

        val actv = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        actv.threshold = 1//will start working from first character

        actv.setAdapter(adapter)//setting the adapter data into the AutoCompleteTextView

        val autoCompleteLeftIconTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteLeftIconTextView)

        autoCompleteLeftIconTextView.threshold = 1//will start working from first character

        autoCompleteLeftIconTextView.setAdapter(adapter)//setting the adapter data into the AutoCompleteTextView

        val autoCompleteRightIconTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteRightIconTextView)

        autoCompleteRightIconTextView.threshold = 1//will start working from first character

        autoCompleteRightIconTextView.setAdapter(adapter)//setting the adapter data into the AutoCompleteTextView
    }

    private fun setDataToMultiAutoCompleteTextView() {
        val TopicName = ArrayAdapter(this, android.R.layout.select_dialog_item, fruits)//fruits list add

        val actv = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteTextView)

        actv.setAdapter<ArrayAdapter<String>>(TopicName) //set it into adapter

        actv.threshold = 1

        actv.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())


        val multiAutoCompleteLeftIconTextView = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteLeftIconTextView)

        multiAutoCompleteLeftIconTextView.setAdapter<ArrayAdapter<String>>(TopicName)

        multiAutoCompleteLeftIconTextView.threshold = 1

        multiAutoCompleteLeftIconTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())



        val multiAutoCompleteRightIconTextView = findViewById<MultiAutoCompleteTextView>(R.id.multiAutoCompleteRightIconTextView)

        multiAutoCompleteRightIconTextView.setAdapter<ArrayAdapter<String>>(TopicName)

        multiAutoCompleteRightIconTextView.threshold = 1

        multiAutoCompleteRightIconTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
    }

    private fun setCheckedTextView() {

        checkedTextView.setOnClickListener {

            if (checkedTextView.isChecked) {

                checkedTextView.setCheckMarkDrawable(R.drawable.baseline_check_box_outline_blank_black_24)
                checkedTextView.isChecked = false
            } else {

                checkedTextView.setCheckMarkDrawable(R.drawable.baseline_check_box_black_24)
                checkedTextView.isChecked = true
            }
        }
    }

    //region Init Toolbar

    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Advanced TextView"

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