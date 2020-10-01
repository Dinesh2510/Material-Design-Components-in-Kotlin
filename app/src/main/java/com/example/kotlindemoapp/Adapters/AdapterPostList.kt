package com.example.kotlindemoapp.Adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlindemoapp.Model.ItemModel
import com.example.kotlindemoapp.Model.Post
import com.example.kotlindemoapp.R
import java.util.ArrayList

class AdapterPostList(private val postList: ArrayList<Post>, private val mCtx: Context) :
    RecyclerView.Adapter<AdapterPostList.PostViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(mCtx)
        val view: View = inflater.inflate(R.layout.item_post, null)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post: Post = postList!![position]
        holder.post_main_name.setText(post.getPost_title())
        holder.title_content.setText(post.getPost_content())
        holder.post_username.setText(post.getPost_username())
        holder.post_date.setText(post.getPost_date())
        Log.d("Post_name", "onBindViewHolder: " + post.getPost_title())
        Glide.with(mCtx!!)
            .load("http://pixeldev.in/webservices/digital_reader/admin/" + post.getPost_image())
            .placeholder(R.drawable.banner)
            .into(holder.post_image)
        Log.d(
            "img_url",
            "onBindViewHolder: " + "http://pixeldev.in/webservices/digital_reader/admin/" + post.getPost_image()
        )
        Log.d("img_of", "onBindViewHolder: " + post.getPost_image())
        holder.itemView.setOnClickListener(View.OnClickListener {
//            val intent = Intent(mCtx, PostDetails::class.java)
//            intent.putExtra("title", post.getPost_title())
//            intent.putExtra("content", post.getPost_content())
//            intent.putExtra("username", post.getPost_username())
//            intent.putExtra("date", post.getPost_date())
//            intent.putExtra("id", post.getPost_id())
//            intent.putExtra("image", post.getPost_image())
//            intent.putExtra("link", postList[position].getPost_link())
//            intent.putExtra("like", postList[position].getPost_like())
//            intent.putExtra("premium", postList[position].getPremium_flag())
//            Log.d("oo", "onClick: " + postList[position].getPremium_flag())
//            mCtx.startActivity(intent)
            Toast.makeText(mCtx, "" + post.getPost_title(), Toast.LENGTH_LONG).show()
        })
    }

    override fun getItemCount(): Int {
        return postList!!.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var post_main_name: TextView
        var title_content: TextView
        var post_content: TextView? = null
        var post_username: TextView
        var post_date: TextView
        var post_image: ImageView
        var bookmark: ImageView? = null

        init {
            post_main_name = itemView.findViewById(R.id.title)
            title_content = itemView.findViewById(R.id.title_content)
            post_username = itemView.findViewById(R.id.post_username)
            post_date = itemView.findViewById(R.id.date)
            post_image = itemView.findViewById(R.id.image)
        }
    }
}