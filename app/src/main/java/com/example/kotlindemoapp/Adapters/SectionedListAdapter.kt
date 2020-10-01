package com.example.kotlindemoapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.Model.SectionGalleryListHolder
import com.example.kotlindemoapp.R


class SectionedListAdapter(private val sectionGalleryListHolderList: List<SectionGalleryListHolder>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val SECTION = 0
    private val ITEM = 1
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: SectionGalleryListHolder, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == SECTION) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sectioned_list_section, parent, false)

            return SectionViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sectioned_list_item, parent, false)

            return PlaceViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(reholder: RecyclerView.ViewHolder, position: Int) {

        if (sectionGalleryListHolderList[position].isSectionTitle) {
            if (reholder is SectionViewHolder) {

                reholder.sectionTitleTextView.text = sectionGalleryListHolderList[position].name

            }

        } else {
            if (reholder is PlaceViewHolder) {
                val section = sectionGalleryListHolderList[position]
                val context = reholder.placeImageView.context
                val id = Utils.getDrawableInt(context, section.image.image)
                Utils.setImageToImageView(context, reholder.placeImageView, id)

                reholder.placeImageView.setOnClickListener { view ->

                    itemClickListener.onItemClick(view, sectionGalleryListHolderList[position], position)

                }

            }
        }

    }

    override fun getItemCount(): Int {
        return sectionGalleryListHolderList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (sectionGalleryListHolderList[position].isSectionTitle) {
            SECTION
        } else {
            ITEM
        }
    }

    inner class PlaceViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var placeImageView: ImageView = view.findViewById(R.id.itemImageView)

    }

    inner class SectionViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var sectionTitleTextView: TextView = view.findViewById(R.id.sectionTitleTextView)

    }

}
