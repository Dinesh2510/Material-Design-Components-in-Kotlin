package com.example.kotlindemoapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.Model.News
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.ui_search_view_toolbar_search_view_item.view.*

import java.util.*

class UiSearchViewToolbarSearchViewAdapter(newsArrayList: ArrayList<News>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val newsArrayList: List<News>
    private var newsArrayListFiltered: List<News>
    private lateinit var itemClickListener: UiSearchViewToolbarSearchViewAdapter.OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: News, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: UiSearchViewToolbarSearchViewAdapter.OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    init {
        this.newsArrayList = newsArrayList
        this.newsArrayListFiltered = newsArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ui_search_view_toolbar_search_view_item, parent, false)

        return PlaceViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is UiSearchViewToolbarSearchViewAdapter.PlaceViewHolder) {

            val news = newsArrayListFiltered[position]

            viewHolder.dateTextView.text = news.date


            val context = viewHolder.newsContainer.context

            val id = Utils.getDrawableInt(context, news.newsImage)
            Utils.setImageToImageView(context, viewHolder.newsImageView, id)

            viewHolder.titleTextView.text = news.title

            viewHolder.categoryTextView.text = news.category




                viewHolder.newsContainer.setOnClickListener { v: View -> itemClickListener.onItemClick(v, newsArrayList[position], position) }

        }
    }

    override fun getItemCount(): Int {
        return newsArrayListFiltered.size
    }

    inner class PlaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var newsImageView: ImageView = view.newsImageView
        var dateTextView: TextView = view.dateTextView
        var titleTextView: TextView = view.titleTextView
        var categoryTextView: TextView = view.categoryTextView
        var newsContainer: ConstraintLayout = view.newsContainer


    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    newsArrayListFiltered = newsArrayList
                } else {
                    val filteredList = ArrayList<News>()
                    for (row in newsArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }

                    newsArrayListFiltered = filteredList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = newsArrayListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                newsArrayListFiltered = filterResults.values as ArrayList<News>
                notifyDataSetChanged()
            }
        }
    }
}
