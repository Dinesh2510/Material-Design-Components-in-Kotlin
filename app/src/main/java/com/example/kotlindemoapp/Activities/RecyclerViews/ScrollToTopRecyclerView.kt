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
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Adapters.ScrollToTopListAdapter
import com.example.kotlindemoapp.Helper.WallpaperItemRepository
import com.example.kotlindemoapp.Model.WallpaperItem
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_scroll_to_top_recycler_view.*

class ScrollToTopRecyclerView : AppCompatActivity() {
    // data and adapter
    internal lateinit var itemList: List<WallpaperItem>
    internal lateinit var adapter: ScrollToTopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_to_top_recycler_view)

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
        itemList = WallpaperItemRepository.wallpaperItemList
    }

    private fun initUI() {
        initToolbar()

        // get list adapter
        adapter = ScrollToTopListAdapter(itemList)

        // get recycler view
        val mLayoutManager = LinearLayoutManager(applicationContext)
        photoRecyclerView.layoutManager = mLayoutManager
        photoRecyclerView.itemAnimator = DefaultItemAnimator()

    }

    private fun initDataBindings() {
        // bind adapter to recycler
        photoRecyclerView.adapter = adapter
        showHideWhenScroll()
    }

    private fun initActions() {
        adapter.setOnItemClickListener(object : ScrollToTopListAdapter.OnItemClickListener{
            override fun onItemClick(view: View, obj: WallpaperItem, position: Int) {
                Toast.makeText(applicationContext, "Selected " + obj.name, Toast.LENGTH_SHORT).show()
            }
        })

        scrollFloatingButton.setOnClickListener { photoRecyclerView.smoothScrollToPosition(0) }
    }

    //region Init Toolbar
    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Scroll To Top Demo"

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

    private fun showHideWhenScroll() {
        photoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                    scrollFloatingButton.show()
                else
                    scrollFloatingButton.hide()
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

}