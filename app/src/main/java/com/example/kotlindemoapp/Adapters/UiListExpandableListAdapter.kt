package com.example.kotlindemoapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.Helper.ViewAnimationUtils
import com.example.kotlindemoapp.Model.GeneralList
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.item_expnadable.view.*


class UiListExpandableListAdapter(private val generalListList: List<GeneralList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: GeneralList, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_expnadable, parent, false)
        return GeneralListViewHolder(itemView)

    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is UiListExpandableListAdapter.GeneralListViewHolder) {
            val financeTransaction = generalListList[position]

            viewHolder.dragImageView.tag = position

            viewHolder.dragImageView.setOnClickListener {v ->
                val show = Utils.toggleUpDownWithAnimation(v)

                if (show) {

                    if (generalListList.size > position) {
                        generalListList[position].isOpened = true
                    }

                    ViewAnimationUtils.expand(viewHolder.detailLayout)
                } else {
                    if (generalListList.size > position) {
                        generalListList[position].isOpened = false
                    }

                    ViewAnimationUtils.collapse(viewHolder.detailLayout)
                }
            }

            val context = viewHolder.rowImageView.context
            if (context != null) {
                val id = Utils.getDrawableInt(context, financeTransaction.image)
                Utils.setImageToImageView(context, viewHolder.rowImageView, id)
            }

            viewHolder.titleTextView.text = financeTransaction.name
            viewHolder.captionTextView.text = financeTransaction.caption
            viewHolder.detailTextView.text = financeTransaction.detail

            if (financeTransaction.isOpened) {
                if (viewHolder.detailLayout.visibility == View.GONE) {
                    viewHolder.detailLayout.visibility = View.VISIBLE
                    Utils.toggleUpDownWithAnimation(viewHolder.dragImageView, 0, 180)
                }
            } else {
                viewHolder.detailLayout.visibility = View.GONE
                Utils.toggleUpDownWithAnimation(viewHolder.dragImageView, 0, 0)
            }

        }
    }


    override fun getItemCount(): Int {

        return generalListList.size

    }

    inner class GeneralListViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var titleTextView: TextView = view.titleTextView
        internal var captionTextView: TextView = view.captionTextView
        internal var detailTextView: TextView = view.detailTextView
        internal var dragImageView: ImageView = view.dragImageView
        internal var rowImageView: ImageView = view.rowImageView
        internal var detailLayout: ConstraintLayout = view.detailLayout

    }
}
