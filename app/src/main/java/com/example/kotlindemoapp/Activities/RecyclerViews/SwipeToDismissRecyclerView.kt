package com.example.kotlindemoapp.Activities.RecyclerViews

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Adapters.SwipeToDismissListWithConfirmAdapter
import com.example.kotlindemoapp.Helper.GeneralListRepository
import com.example.kotlindemoapp.Helper.OnStartDragListener
import com.example.kotlindemoapp.Helper.RecyclerItemTouchHelper
import com.example.kotlindemoapp.Model.GeneralList
import com.example.kotlindemoapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_swipe_to_dismiss_recycler_view.*

class SwipeToDismissRecyclerView : AppCompatActivity(), OnStartDragListener,
    RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    private lateinit var mItemTouchHelper: ItemTouchHelper
    private lateinit var conordiLayout: CoordinatorLayout
    private lateinit var generalListList: MutableList<GeneralList>
    private lateinit var adapter: SwipeToDismissListWithConfirmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_to_dismiss_recycler_view)

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
    }

    private fun initUI() {
        initToolbar()

        // get list adapter
        adapter = SwipeToDismissListWithConfirmAdapter(this, generalListList)
        conordiLayout = findViewById(R.id.coordinator)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false

        val itemTouchHelperCallback = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
    }

    private fun initDataBindings() {
        // bind adapter to recycler
        recyclerView.adapter = adapter
    }

    private fun initActions() {
        adapter.setOnItemClickListener(object :
            SwipeToDismissListWithConfirmAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: GeneralList, position: Int) {
                Toast.makeText(
                    this@SwipeToDismissRecyclerView,
                    "Selected : " + obj.name,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        mItemTouchHelper.startDrag(viewHolder)
    }

    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.md_white_1000
                ), PorterDuff.Mode.SRC_ATOP
            )
        }

        toolbar.title = "Swipe To Dismiss List with Confirm"

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

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is SwipeToDismissListWithConfirmAdapter.GeneralListViewHolder) {
            // get the removed item name to display it in snack bar
            val name = generalListList[viewHolder.getAdapterPosition()].name

            // backup of removed item for undo purpose
            val deletedItem = generalListList[viewHolder.getAdapterPosition()]
            val deletedIndex = viewHolder.getAdapterPosition()

            // remove the item from recycler view
            adapter.removeItem(viewHolder.getAdapterPosition())

            // showing snack bar with Undo option
            val snackbar = Snackbar
                .make(conordiLayout, "$name removed from cart!", Snackbar.LENGTH_LONG)
            snackbar.setAction("UNDO") {

                // undo is selected, restore the deleted item
                adapter.restoreItem(deletedItem, deletedIndex)
            }
            snackbar.setActionTextColor(Color.YELLOW)
            snackbar.show()
        }
    }
}