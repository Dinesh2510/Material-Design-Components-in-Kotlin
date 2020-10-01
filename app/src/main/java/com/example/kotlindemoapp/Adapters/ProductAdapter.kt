package com.example.kotlindemoapp.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemoapp.Activities.DetailsActivity
import com.example.kotlindemoapp.Model.ItemModel
import com.example.kotlindemoapp.R

class ProductAdapter(private val examplelist: List<ItemModel>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list,
            parent, false
        )
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val ItemModel = examplelist[position]
        holder.image.setImageResource(ItemModel.image)
        holder.title1.text = ItemModel.title
        holder.title2.text = ItemModel.title2

        holder.itemView.setOnClickListener() {
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("title1", ItemModel.title)
            intent.putExtra("title2", ItemModel.title2)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return examplelist.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image_view)
        val title1: TextView = itemView.findViewById(R.id.text_view_1)
        val title2: TextView = itemView.findViewById(R.id.text_view_2)
    }

}