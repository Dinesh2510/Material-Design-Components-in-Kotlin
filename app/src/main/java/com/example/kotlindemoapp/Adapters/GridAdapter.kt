package com.example.kotlindemoapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlindemoapp.Model.Country
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.grid_item.view.*
import java.util.ArrayList


class GridAdapter(private val countryArrayList: ArrayList<Country>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Country, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)

        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is CountryViewHolder) {

            val country = countryArrayList[position]

            viewHolder.countryNameTextView.text = country.name

            val context = viewHolder.countryCardView.context

//            val id = Utils.getDrawableInt(context, country.imageName)
//            Utils.setImageToImageView(context, viewHolder.countryImageView, id)
            Glide.with(viewHolder.itemView.context).load(country.imageName)
                .into(viewHolder.countryImageView);


            viewHolder.countryCardView.setOnClickListener { v: View ->
                itemClickListener.onItemClick(
                    v,
                    countryArrayList[position],
                    position
                )
            }

        }
    }

    override fun getItemCount(): Int {
        return countryArrayList.size
    }

    inner class CountryViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var countryNameTextView: TextView = view.countryNameTextView
        internal var countryImageView: ImageView = view.countryImageView
        internal var countryCardView: CardView = view.countryCardView

    }
}