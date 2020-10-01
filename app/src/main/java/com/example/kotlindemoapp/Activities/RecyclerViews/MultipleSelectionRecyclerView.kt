package com.example.kotlindemoapp.Activities.RecyclerViews

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Adapters.MultipleSelectionListAdapter
import com.example.kotlindemoapp.Helper.AlertDialogHelper
import com.example.kotlindemoapp.Helper.GeneralChatListRepository
import com.example.kotlindemoapp.Helper.RecyclerItemClickListener
import com.example.kotlindemoapp.Model.Chat
import com.example.kotlindemoapp.R
import java.util.ArrayList

class MultipleSelectionRecyclerView : AppCompatActivity(), AlertDialogHelper.AlertDialogListener {
    private var mActionMode: ActionMode? = null
    private lateinit var toolbar: Toolbar
    private lateinit var multiSelectAdapter: MultipleSelectionListAdapter
    private var isMultiSelect = false

    private var user_list: ArrayList<Chat> = ArrayList()
    private var multiSelectList = ArrayList<Chat>()

    private lateinit var alertDialogHelper: AlertDialogHelper

    private val mActionModeCallback = object : ActionMode.Callback {

        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items
            val inflater = mode.menuInflater
            inflater.inflate(R.menu.menu_multi_selected, menu)

            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.action_delete -> {
                    alertDialogHelper.showAlertDialog("", "Delete Contact", "DELETE", "CANCEL", 1, false)
                    return true
                }
                else -> return false
            }

        }

        override fun onDestroyActionMode(mode: ActionMode) {
            isMultiSelect = false
            multiSelectList = ArrayList()
            refreshAdapter()

            toolbar.visibility = View.VISIBLE
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_selection_recycler_view)

        alertDialogHelper = AlertDialogHelper(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        toolbar = findViewById(R.id.toolbar)
        dataLoad()

        initToolbar()
        multiSelectAdapter = MultipleSelectionListAdapter(this, user_list, multiSelectList)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = multiSelectAdapter

        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(this, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                if (isMultiSelect)
                    multiSelect(position)
                else
                    Toast.makeText(applicationContext, "Details Page", Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClick(view: View?, position: Int) {
                toolbar.visibility = View.GONE
                if (!isMultiSelect) {
                    multiSelectList = ArrayList()
                    isMultiSelect = true


                    mActionMode = startActionMode(mActionModeCallback)

                }
                multiSelect(position)

            }
        }))
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        when (id) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_filter -> {
                Toast.makeText(applicationContext, "Clicked Edit.", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun dataLoad() {
        user_list = GeneralChatListRepository.generalChatList
    }


    fun multiSelect(position: Int) {
        if (multiSelectList.contains(user_list[position]))
            multiSelectList.remove(user_list[position])
        else
            multiSelectList.add(user_list[position])

        if (multiSelectList.size > 0) {
            mActionMode?.title = "" + multiSelectList.size
        }else {
            mActionMode?.title = ""
        }
        refreshAdapter()





    }


    fun refreshAdapter() {
        multiSelectAdapter.selected_usersList = multiSelectList
        multiSelectAdapter.usersList = user_list
        multiSelectAdapter.notifyDataSetChanged()

    }

    // AlertDialog Callback Functions

    override fun onPositiveClick(from: Int) {
        if (from == 1) {
            if (multiSelectList.size > 0) {
                for (i in multiSelectList.indices)
                    user_list.remove(multiSelectList[i])

                multiSelectAdapter.notifyDataSetChanged()


                mActionMode?.finish()

                Toast.makeText(applicationContext, "Delete Click", Toast.LENGTH_SHORT).show()
            }
        } else if (from == 2) {

            mActionMode?.finish()


            val mSample = Chat("ID" + user_list.size, "Name" + user_list.size, "Description" + user_list.size, "Image" + user_list.size, "Count" + user_list.size, "Date" + user_list.size)
            user_list.add(mSample)
            multiSelectAdapter.notifyDataSetChanged()

        }
    }

    override fun onNegativeClick(from: Int) {

    }

    override fun onNeutralClick(from: Int) {

    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.baseline_menu_black_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Multiple Selection List"

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
}