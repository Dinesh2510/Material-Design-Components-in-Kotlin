package com.example.kotlindemoapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Adapters.ProductAdapter
import com.example.kotlindemoapp.Model.ItemModel
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.fragment_call.*


class Call : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_call, container, false)
        val recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view);

        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.setHasFixedSize(true)
        val products = ArrayList<ItemModel>()
        products.add(ItemModel(R.drawable.banner, "Video1", "Kotlin RecyclerView tutorial"))
        products.add(ItemModel(R.drawable.banner, "Video2", "Kotlin RecyclerView tutorial"))
        products.add(ItemModel(R.drawable.banner, "Video3", "Kotlin RecyclerView tutorial"))
        products.add(ItemModel(R.drawable.banner, "Video4", "Kotlin RecyclerView tutorial"))

        val adapter = ProductAdapter(products)

        //now adding the adapter to recyclerview
        recycler_view.adapter = adapter

        return view
    }

    companion object {
        fun newInstance(): Call = Call()
    }

}