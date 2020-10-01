package com.example.kotlindemoapp.Activities.RecyclerViews

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Adapters.GridAdapter
import com.example.kotlindemoapp.Model.Country
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_grid_recycler.*
import java.util.ArrayList

class GridRecyclerWithTwoCol : AppCompatActivity() {
    // data and adapter
    //internal lateinit var adapter: GridAdapter
    internal var numOfColumns = 2

    // RecyclerView
    internal lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_recycler)

        Toast.makeText(this, "Switch On Internet to Load Image", Toast.LENGTH_LONG).show()
        initUI()

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_sort, menu)
//        return super.onCreateOptionsMenu(menu)
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(this, "Clicked " + item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initUI() {
        initToolbar()
        // get list adapter
        // adapter = GridAdapter(countryArrayList)

        // get recycler view
        recyclerView = findViewById(R.id.countryListRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, numOfColumns)
        recyclerView.itemAnimator = DefaultItemAnimator()

        val countryArrayList = ArrayList<Country>()
        countryArrayList.add(
            Country(
                "India",
                "Main",
                "https://i.picsum.photos/id/5/200/300.jpg?hmac=1TWjKFT7_MRP0ApEyDUA3eCP0HXaKTWJfHgVjwGNoZU",
                "Kotlin RecyclerView tutorial"
            )
        )
        countryArrayList.add(
            Country(
                "US",
                "DEs",
                "https://i.picsum.photos/id/5/200/300.jpg?hmac=1TWjKFT7_MRP0ApEyDUA3eCP0HXaKTWJfHgVjwGNoZU",
                "0"
            )
        )
        countryArrayList.add(
            Country(
                "UK",
                "DEs",
                "https://i.picsum.photos/id/5/200/300.jpg?hmac=1TWjKFT7_MRP0ApEyDUA3eCP0HXaKTWJfHgVjwGNoZU",
                "0"
            )
        )
        countryArrayList.add(
            Country(
                "Daman",
                "DEs",
                "https://i.picsum.photos/id/5/200/300.jpg?hmac=1TWjKFT7_MRP0ApEyDUA3eCP0HXaKTWJfHgVjwGNoZU",
                "0"
            )
        )

        val adapter = GridAdapter(countryArrayList)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : GridAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: Country, position: Int) {
                Toast.makeText(this@GridRecyclerWithTwoCol, "Selected : " + obj.name, Toast.LENGTH_LONG)
                    .show()
            }
        })

    }

    //region Init Toolbar
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

        toolbar.title = "City Grid 1"

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