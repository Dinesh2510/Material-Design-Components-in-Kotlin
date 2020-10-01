package com.example.kotlindemoapp.Activities.RecyclerViews

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemoapp.Adapters.ProductAdapter
import com.example.kotlindemoapp.Model.ItemModel
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_basic_recyler_views.*

class BasicRecylerViews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_recyler_views)
        initToolbar()

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        val products = ArrayList<ItemModel>()
        products.add(ItemModel(R.drawable.banner, "Video1", "Kotlin RecyclerView tutorial"))
        products.add(ItemModel(R.drawable.banner, "Video2", "Kotlin RecyclerView tutorial"))
        products.add(ItemModel(R.drawable.banner, "Video3", "Kotlin RecyclerView tutorial"))
        products.add(ItemModel(R.drawable.banner, "Video4", "Kotlin RecyclerView tutorial"))

        val adapter = ProductAdapter(products)

        //now adding the adapter to recyclerview
        recycler_view.adapter = adapter


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

        toolbar.title = "Basic RecyclerView"


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

}