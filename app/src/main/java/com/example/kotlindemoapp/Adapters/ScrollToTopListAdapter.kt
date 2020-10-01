package com.example.kotlindemoapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.Model.WallpaperItem
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.scroll_to_top_list_item.view.*


class ScrollToTopListAdapter(private val itemList: List<WallpaperItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: ScrollToTopListAdapter.OnItemClickListener


    interface OnItemClickListener {
        fun onItemClick(view: View, obj: WallpaperItem, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: ScrollToTopListAdapter.OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.scroll_to_top_list_item, parent, false)

        return PlaceViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is ScrollToTopListAdapter.PlaceViewHolder) {

            val item = itemList[position]

            viewHolder.likeCountTextView.text = item.likeCount
            viewHolder.viewCountTextView.text = item.viewCount
            viewHolder.nameTextView.text = item.user.nameName
            viewHolder.agoTextView.text = item.user.ago

            val context = viewHolder.holderCardView.context

            val id = Utils.getDrawableInt(context, item.imageName)
            Utils.setImageToImageView(context, viewHolder.itemImageView, id)

            val userImg = Utils.getDrawableInt(context, item.user.user_image!!)
            Utils.setCircleImageToImageView(context, viewHolder.userImageView, userImg, 0, 0)


            viewHolder.holderCardView.setOnClickListener { v: View -> itemClickListener.onItemClick(v, itemList[position], position) }


        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class PlaceViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var itemImageView: ImageView = view.itemImageView
        internal var likeCountTextView: TextView = view.likeCountTextView
        internal var viewCountTextView: TextView = view.viewCountTextView
        internal var holderCardView: CardView = view.holderCardView
        internal var nameTextView: TextView = view.nameTextView
        internal var agoTextView: TextView = view.agoTextView
        internal var userImageView: ImageView = view.userImageView


    }
}

