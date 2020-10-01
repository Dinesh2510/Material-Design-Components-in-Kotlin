package com.example.kotlindemoapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.Model.MediaMusic
import com.example.kotlindemoapp.R


class MusicPlayerPagerAdapter(private val context: Context, private val MediaMp3ObjList: List<MediaMusic>) : PagerAdapter() {

    lateinit var itemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(view: View?, obj: MediaMusic?, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.itemClickListener = mItemClickListener!!
    }

    override fun getCount(): Int {
        return MediaMp3ObjList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_music, container, false)
        val imageView = view.findViewById<ImageView>(R.id.placeImageView)


        val context = container.context

        val id = Utils.getDrawableInt(context, MediaMp3ObjList[position].image)
        Utils.setImageToImageView(context, imageView, id)

        val vp = container as ViewPager
        vp.addView(view, 0)

        return view

    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}