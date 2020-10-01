package com.example.kotlindemoapp.Activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.Adapters.UiContainerCustomSpinnerAdapter
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_spinner.*
import java.util.*

class SpinnerActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    internal lateinit var list: ArrayList<String>
    internal lateinit var list2: ArrayList<String>
    internal lateinit var adapter: ArrayAdapter<String>
    internal lateinit var adapter2: ArrayAdapter<String>

    internal lateinit var fruits: Array<String>

    var fruits_custom = arrayOf("Email", "Home", "Person")
    var icons = intArrayOf(
        R.drawable.baseline_person_outline_black_24,
        R.drawable.baseline_person_outline_black_24,
        R.drawable.baseline_person_outline_black_24
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        initToolbar()

        fruits = resources.getStringArray(R.array.fruits)

        spinner_basic.onItemSelectedListener = this


        list = ArrayList(Arrays.asList(*fruits))

        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_basic.adapter = adapter


        custom_spinner.onItemSelectedListener = this

        list2 = ArrayList(Arrays.asList(*fruits_custom))

        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list2)

        val uiContainerCustomSpinnerAdapter =
            UiContainerCustomSpinnerAdapter(applicationContext, icons, fruits_custom)
        custom_spinner.adapter = uiContainerCustomSpinnerAdapter


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

        toolbar.title = "Basic Spinner"


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

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

        if (parent.getId() == R.id.spinner_basic) {
            val item = parent.getItemAtPosition(position).toString()
            textView.text = item

        } else if (parent.getId() == R.id.custom_spinner) {

            val items = fruits_custom[position]

            selectedSpinnerTextView.text = items
        }

//


    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }

}