package com.example.kotlindemoapp.Activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_alert_dialog.*
import kotlinx.android.synthetic.main.activity_alert_dialog.toolbar
import kotlinx.android.synthetic.main.activity_date_time_picker.*
import java.text.SimpleDateFormat
import java.util.*

class DateTimePicker : AppCompatActivity() {

    internal var dateTime = Calendar.getInstance()

    val datePickerDialog = (object : DatePickerDialog.OnDateSetListener{
        override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
            dateTime.set(Calendar.YEAR, year)
            dateTime.set(Calendar.MONTH, monthOfYear)
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDate()
        }
    })


    val timePickerDialog = (object : TimePickerDialog.OnTimeSetListener {
        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            dateTime.set(Calendar.MINUTE, minute)
            updateTime()
        }

    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_time_picker)
        initToolbar()

        initUI()

    }
    private fun initUI() {
        val button = findViewById<Button>(R.id.getDateButton)
        button.setOnClickListener { openDatePicker() }

        val buttont = findViewById<Button>(R.id.getTimeButton)
        buttont.setOnClickListener { openTimePicker() }
    }

    private fun openDatePicker() {
        DatePickerDialog(this, datePickerDialog, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun updateDate() {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val shortTimeStr = sdf.format(dateTime.time)
        dateTextView.text = shortTimeStr
    }
    private fun openTimePicker() {
        TimePickerDialog(this, timePickerDialog, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show()
    }

    private fun updateTime() {
        val sdf = SimpleDateFormat("HH:mm aa", Locale.US)
        val shortTimeStr = sdf.format(dateTime.time)
        timeTextView.text = shortTimeStr
    }

    //region Init Toolbar

    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Date Picker"

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