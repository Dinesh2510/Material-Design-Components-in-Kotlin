package com.example.kotlindemoapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlindemoapp.Activities.RecyclerViews.*
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_custom_recycler_view.*

class CustomRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_recycler_view)


        btn_rv_1.setOnClickListener {
            val intent = Intent(this, BasicRecylerViews::class.java)
            startActivity(intent)
        }
        btn_rv_2.setOnClickListener {
            val intent = Intent(this, ExpandableRecyclerView::class.java)
            startActivity(intent)
        }
        btn_rv_3.setOnClickListener {
            val intent = Intent(this, GridRecyclerWithTwoCol::class.java)
            startActivity(intent)
        }
        btn_rv_4.setOnClickListener {
            val intent = Intent(this, ScrollToTopRecyclerView::class.java)
            startActivity(intent)
        }
        btn_rv_5.setOnClickListener {
            val intent = Intent(this, SwipeToDismissRecyclerView::class.java)
            startActivity(intent)
        }
        btn_rv_6.setOnClickListener {
            val intent = Intent(this, SectionRecyclerView::class.java)
            startActivity(intent)
        }
        btn_rv_7.setOnClickListener {
            val intent = Intent(this, MultipleSelectionRecyclerView::class.java)
            startActivity(intent)
        }


    }
}