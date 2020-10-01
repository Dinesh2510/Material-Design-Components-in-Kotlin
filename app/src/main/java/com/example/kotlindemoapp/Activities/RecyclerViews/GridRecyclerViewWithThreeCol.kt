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
import com.example.kotlindemoapp.Adapters.GridWithThreeColumnsAdapter
import com.example.kotlindemoapp.Helper.CourseListRepository
import com.example.kotlindemoapp.Model.Course
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_grid_recycler_view_with_three_col.*


class GridRecyclerViewWithThreeCol : AppCompatActivity() {

    // data and adapter
    internal lateinit var courseList: List<Course>
    internal lateinit var adapter: GridWithThreeColumnsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_recycler_view_with_three_col)

        initData()

        initUI()

        initDataBindings()

        initActions()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {
        // get place list
        courseList = CourseListRepository.courseList
    }

    private fun initUI() {
        initToolbar()

        // get list adapter
        adapter = GridWithThreeColumnsAdapter(courseList)

        val mLayoutManager = GridLayoutManager(applicationContext, 3)
        videoRecyclerView.layoutManager = mLayoutManager
        videoRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun initDataBindings() {
        // bind adapter to recycler
        videoRecyclerView.adapter = adapter
    }

    private fun initActions() {
        adapter.setOnItemClickListener(object : GridWithThreeColumnsAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: Course, position: Int) {
                Toast.makeText(applicationContext, "Clicked " + obj.title, Toast.LENGTH_SHORT)
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

        toolbar.title = "Grid with three columns"

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