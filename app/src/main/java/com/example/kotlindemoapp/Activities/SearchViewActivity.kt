package com.example.kotlindemoapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_search_view.*

class SearchViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        btn_1.setOnClickListener {
            val intent = Intent(this, BasicSearchViewActivity::class.java)
            startActivity(intent)
        }
        btn_2.setOnClickListener {
            val intent = Intent(this, ToolbarSearchViewActivity::class.java)
            startActivity(intent)
        }
    }
}