package com.example.kotlindemoapp.Activities

import android.content.Context
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlindemoapp.Adapters.AdapterPostList
import com.example.kotlindemoapp.Model.Post
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_post_list.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*


//http://pixeldev.in/webservices/digital_reader/GetAllPostList.php
class PostList : AppCompatActivity() {

    private lateinit var membersList: ArrayList<Post>
    private lateinit var adapter: AdapterPostList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        initToolbar()
        val recyclerView_all_post = findViewById<RecyclerView>(R.id.recyclerView_all_post)
        recyclerView_all_post.setHasFixedSize(true)
        recyclerView_all_post.setLayoutManager(LinearLayoutManager(this))
        val mLayoutManager = LinearLayoutManager(this)
        membersList = ArrayList<Post>()
        if (isConnected()) {
            loadMembers()
        } else {
            Toast.makeText(this, "Network connection is not available", Toast.LENGTH_SHORT)
                .show();
        }
        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({
                if (isConnected()) {
                    loadMembers()
                    membersList.clear()
                } else {
                    Toast.makeText(this, "Network connection is not available", Toast.LENGTH_SHORT)
                        .show();
                }
                swipe_refresh.isRefreshing = false

            }, 2000)
        }


    }

    fun isConnected(): Boolean {
        var connected = false
        try {
            val cm =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cm.activeNetworkInfo
            connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
            return connected
        } catch (e: java.lang.Exception) {
            Log.e("Connectivity Exception", e.message)
        }
        return connected
    }

    private fun loadMembers() {
        // val url = "https://api.myjson.com/bins/xbspb"
        val url = "http://pixeldev.in/webservices/digital_reader/GetAllPostList.php"
        val stringRequest: StringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
//                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                try {
                    if (!response.equals("failure", ignoreCase = true)) {
                        try {
                            val obj = JSONObject(response)
                            val array = obj.getJSONArray("response")
                            // JSONArray array = new JSONArray("response");
                            for (i in 0 until array.length()) {
                                val rlist = array.getJSONObject(i)
                                Log.i("rlist", "[$rlist]")
                                membersList!!.add(
                                    Post(
                                        rlist.getString("post_id"),
                                        rlist.getString("post_title"),
                                        rlist.getString("post_sub_title"),
                                        rlist.getString("post_content"),
                                        rlist.getString("post_userid"),
                                        rlist.getString("post_username"),
                                        rlist.getString("post_date"),
                                        rlist.getString("post_image"),
                                        rlist.getString("post_link"),
                                        rlist.getString("topics"),
                                        rlist.getString("post_view"),
                                        rlist.getString("post_like"),
                                        rlist.getString("premium_flag")
                                    )
                                )
                            }

                            //creating adapter object and setting it to recyclerview

                            adapter = AdapterPostList(membersList, this)
                            recyclerView_all_post.setAdapter(adapter)
                            setDataAdapter()
                        } catch (e: JSONException) {
                            e.printStackTrace()

                        }
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }) {
//            override fun getParams(): Map<String, String> {
//                val params: MutableMap<String, String> = HashMap()
//                //Change with your post params
//                params["username"] = username
//                params["password"] = password
//                return params
//            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)

    }

    private fun setDataAdapter() {
        if (membersList.size > 0) {
            adapter = AdapterPostList(membersList, this)

        } else {
            Toast.makeText(this, "btm < 0", Toast.LENGTH_LONG).show()

        }
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

        toolbar.title = "Kotlin Volley RecyclerView"

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
        } else {
            Toast.makeText(this, "Clicked " + item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    //Pop Menu Function
}