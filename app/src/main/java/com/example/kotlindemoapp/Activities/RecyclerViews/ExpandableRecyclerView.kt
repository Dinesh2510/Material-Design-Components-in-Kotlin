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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemoapp.Adapters.UiListExpandableListAdapter
import com.example.kotlindemoapp.Helper.GeneralListRepository
import com.example.kotlindemoapp.Model.GeneralList
import com.example.kotlindemoapp.Model.ItemModel
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_expandable_recycler_view.*

class ExpandableRecyclerView : AppCompatActivity() {
    private lateinit var generalListList: List<GeneralList>
    private lateinit var adapter: UiListExpandableListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_recycler_view)

        initData()

        initUI()

        initDataBindings()

        initActions()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {

        // get place list
        generalListList = GeneralListRepository.generalList

        /* you can add this list in this way also
        val products = ArrayList<GeneralList>()
        products.add(GeneralList("Video1", "Kotlin RecyclerView tutorial", "", ""))
        */
    }

    private fun initUI() {
        initToolbar()

        // get list adapter
        adapter = UiListExpandableListAdapter(generalListList)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false

    }

    private fun initDataBindings() {
        // bind adapter to recycler
        recyclerView.adapter = adapter
    }

    private fun initActions() {
        adapter.setOnItemClickListener(object : UiListExpandableListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: GeneralList, position: Int) {
                Toast.makeText(
                    this@ExpandableRecyclerView,
                    "Selected : " + obj.name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
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

        toolbar.title = "Expandable List"

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