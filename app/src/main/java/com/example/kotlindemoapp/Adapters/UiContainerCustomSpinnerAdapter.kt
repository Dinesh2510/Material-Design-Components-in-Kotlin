package com.example.kotlindemoapp.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.ui_container_custom_spinner_item.view.*

@Suppress("NAME_SHADOWING")
class UiContainerCustomSpinnerAdapter(applicationContext: Context, private val icons: IntArray, private val fruits: Array<String>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(applicationContext)
    }

    override fun getCount(): Int {
        return icons.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        if(view == null){
            view = layoutInflater.inflate(R.layout.ui_container_custom_spinner_item, viewGroup, false)
        }
        val icon = view?.imageView
        val names = view?.textView

        icon?.setImageResource(icons[i])
        names?.text = fruits[i]

        return view!!
    }
}