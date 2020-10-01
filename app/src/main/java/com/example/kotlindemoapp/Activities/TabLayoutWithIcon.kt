package com.example.kotlindemoapp.Activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kotlindemoapp.R
import com.google.android.material.tabs.TabLayout
import com.panaceasoft.pskotlinmaterial.fragment.uicomponent.container.tablayoutwithicon.*
import kotlinx.android.synthetic.main.activity_tab_layout_with_icon.*
import java.util.ArrayList

class TabLayoutWithIcon : AppCompatActivity() {
    private val tabIcons = intArrayOf(
        R.drawable.baseline_camera_24,
        R.drawable.baseline_people_black_24,
        R.drawable.baseline_check_box_black_24,
        R.drawable.baseline_chatbubble_orange_24,
        R.drawable.baseline_download_arrow_grey_24
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout_with_icon)
        initToolbar()
        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabIconColor =
                    ContextCompat.getColor(this@TabLayoutWithIcon, R.color.md_deep_purple_900)
                if (tab.icon != null) {
                    tab.icon?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabIconColor =
                    ContextCompat.getColor(this@TabLayoutWithIcon, R.color.md_grey_400)
                if (tab.icon != null) {
                    tab.icon?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        setupTabIcons()

    }

    private fun setupTabIcons() {

        if (tabLayout.getTabAt(0) != null) {
            val position = tabLayout.getTabAt(0)?.setIcon(tabIcons[0])
            val tabIconColor = ContextCompat.getColor(this, R.color.md_deep_purple_900)
            if (position?.icon != null) {
                position.icon?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

        }
        if (tabLayout.getTabAt(1) != null) {
            tabLayout.getTabAt(1)?.setIcon(tabIcons[1])
        }
        if (tabLayout.getTabAt(2) != null) {
            tabLayout.getTabAt(2)?.setIcon(tabIcons[2])
        }
        if (tabLayout.getTabAt(3) != null) {
            tabLayout.getTabAt(3)?.setIcon(tabIcons[3])
        }
        if (tabLayout.getTabAt(4) != null) {
            tabLayout.getTabAt(4)?.setIcon(tabIcons[4])
        }

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(MapFragment(), "")
        adapter.addFrag(ContactFragment(), "")
        adapter.addFrag(MusicFragment(), "")
        adapter.addFrag(FavouriteFragment(), "")
        adapter.addFrag(SearchFragment(), "")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }

    }

    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.md_black_1000
                ), PorterDuff.Mode.SRC_ATOP
            )
        }

        toolbar.title = "Tab Layout with Icon"

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_black_1000))
        } catch (e: Exception) {
            Log.e("TEAMPS", "Can't set color.")
        }

        try {
            setSupportActionBar(toolbar)
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set support action bar.")
        }

        try {
            if (supportActionBar != null) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        } catch (e: Exception) {
            Log.e("TEAMPS", "Error in set display home as up enabled.")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}