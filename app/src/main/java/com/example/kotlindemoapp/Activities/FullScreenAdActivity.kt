package com.example.kotlindemoapp.Activities

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_full_screen_ad.*

class FullScreenAdActivity : AppCompatActivity() {
    lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_ad)
        initToolbar()

        prepareFullScreenAds()
        Utils.setImageToImageView(applicationContext, image1ImageView, R.drawable.cafe1)

        showAdsButton.isEnabled = false
        showAdsButton.text = "Loading Ads"
        showAdsButton.setOnClickListener { showFullScreenAds() }
    }

    private fun prepareFullScreenAds() {
        mInterstitialAd = InterstitialAd(this)

        // set the ad unit ID
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"

        val adRequest = AdRequest.Builder().build()

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest)

        mInterstitialAd.adListener = object : AdListener() {

            override fun onAdFailedToLoad(i: Int) {
                Log.d("TEAMPS", "Failed to load.$i")
                super.onAdFailedToLoad(i)
            }

            override fun onAdClosed() {
                showAdsButton.isEnabled = false
                showAdsButton.text = "Loading Ads"
                prepareFullScreenAds()
                super.onAdClosed()
            }

            override fun onAdLoaded() {
                showAdsButton.isEnabled = true
                showAdsButton.text = "Ads is ready. Click here!"

            }
        }
    }

    fun showFullScreenAds(): Boolean {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
            return true
        }
        return false
    }

    //region Init Toolbar

    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "AdView FullScreen"

        try {
            toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.md_white_1000))
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