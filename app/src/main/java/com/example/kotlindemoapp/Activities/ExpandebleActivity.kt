package com.example.kotlindemoapp.Activities

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.Helper.RangeSeekBar
import com.example.kotlindemoapp.Helper.Utils
import com.example.kotlindemoapp.Helper.ViewAnimationUtils
import com.example.kotlindemoapp.R
import kotlinx.android.synthetic.main.activity_expandeble.*


class ExpandebleActivity : AppCompatActivity() {

    private lateinit var seekBar: RangeSeekBar<Int>
    private var size1Status: Boolean = false
    private var size2Status: Boolean = false
    private var size3Status: Boolean = false
    private var size4Status: Boolean = false
    private var size5Status: Boolean = false

    private var color1Status: Boolean = true
    private var color2Status: Boolean = false
    private var color3Status: Boolean = false
    private var color4Status: Boolean = false
    private var color5Status: Boolean = false
    private var color6Status: Boolean = false
    private var color7Status: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandeble)

        initData()

        initUI()

        initDataBinding()

        initActions()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initData() {

    }

    private fun initUI() {

        // Init Toolbar
        initToolbar()

        priceRangeLayout.visibility = View.GONE
        colorLayout.visibility = View.GONE
        sizeLayout.visibility = View.GONE
        materialLayout.visibility = View.GONE

        val maxValue2 = 100
        seekBar = RangeSeekBar(0, maxValue2, this)

        seekBar.selectedMinValue = 0
        seekBar.setAbsoluteMaxValue(100)

        price_range_bar_container.addView(seekBar)

        setDefaultCircleImage(color1BgImageView, R.color.md_white_1000)
        setDefaultCircleImage(color2BgImageView, R.color.md_grey_400)
        setDefaultCircleImage(color3BgImageView, R.color.md_yellow_400)
        setDefaultCircleImage(color4BgImageView, R.color.green_primary)
        setDefaultCircleImage(color5BgImageView, R.color.green_primary_dark)
        setDefaultCircleImage(color6BgImageView, R.color.colorPrimary)
        setDefaultCircleImage(color7BgImageView, R.color.md_black_1000)


        val selectedList = ContextCompat.getDrawable(this,R.drawable.baseline_selected_list_24)
        selectedList?.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN)


        setDefaultCircleImage(size1BgImageView, R.color.md_grey_400)
        setDefaultCircleImage(size2BgImageView, R.color.md_grey_400)
        setDefaultCircleImage(size3BgImageView, R.color.md_grey_400)
        setDefaultCircleImage(size4BgImageView, R.color.md_grey_400)
        setDefaultCircleImage(size5BgImageView, R.color.md_grey_400)

        // Set Color Default
        color1ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24)
        color1Status = true
        updateColorTitle()

        // Set Size Default
        setSelectUnSelectSizeFilter(size3BgImageView, R.color.colorPrimary, size3TextView, R.color.md_white_1000)
        size3Status = true
        updateSizeTitle()

        // Set Material Default
        material1Button.isSelected = true

    }

    private fun setDefaultCircleImage(imageView: ImageView, color: Int) {
        Utils.setCircleImageToImageView(applicationContext, imageView, R.drawable.button_tag_with_state, 0, 0)
        imageView.setColorFilter(ContextCompat.getColor(this,color), PorterDuff.Mode.SRC_IN)
    }

    private fun setSelectUnSelectSizeFilter(imageView: ImageView, bgColor: Int, textView: TextView, color: Int) {
        imageView.setColorFilter(ContextCompat.getColor(this,bgColor), PorterDuff.Mode.SRC_IN)
        textView.setTextColor(ContextCompat.getColor(this,color))
    }

    private fun initDataBinding() {

    }

    private fun initActions() {
        seekBar.setOnRangeSeekBarChangeListener(object : RangeSeekBar.OnRangeSeekBarChangeListener<Int> {
            override fun onRangeSeekBarValuesChanged(bar: RangeSeekBar<*>, minValue: Int, maxValue: Int) {
                Log.d("TEAMPS", "initUI: $minValue : $maxValue")

                val minStr = "$ $minValue"
                val maxStr = "$ $maxValue"
                priceRangeFromTitleValueTextView.text = minStr
                priceRangeFromValueTextView.text = minStr
                priceRangeToTitleValueTextView.text = maxStr
                priceRangeToValueTextView.text = maxStr
            }
        })


        priceRangeUpDownImageView.setOnClickListener { v: View ->
            val show = Utils.toggleUpDownWithAnimation(v)
            if (show) {
                ViewAnimationUtils.expand(priceRangeLayout)
            } else {
                ViewAnimationUtils.collapse(priceRangeLayout)
            }
        }

        sizeUpDownImageView.setOnClickListener { v: View ->
            val show = Utils.toggleUpDownWithAnimation(v)
            if (show) {
                ViewAnimationUtils.expand(sizeLayout)
            } else {
                ViewAnimationUtils.collapse(sizeLayout)
            }
        }

        colorUpDownImageView.setOnClickListener { v: View ->
            val show = Utils.toggleUpDownWithAnimation(v)
            if (show) {
                ViewAnimationUtils.expand(colorLayout)
            } else {
                ViewAnimationUtils.collapse(colorLayout)
            }
        }

        materialUpDownImageView.setOnClickListener { v: View ->
            val show = Utils.toggleUpDownWithAnimation(v)
            if (show) {
                ViewAnimationUtils.expand(materialLayout)
            } else {
                ViewAnimationUtils.collapse(materialLayout)
            }
        }


        //region Size
        size1TextView.setOnClickListener {
            if (size1Status) {
                setSelectUnSelectSizeFilter(size1BgImageView, R.color.md_grey_400, size1TextView, R.color.md_grey_800)
                size1Status = false
            } else {
                setSelectUnSelectSizeFilter(size1BgImageView, R.color.colorPrimary, size1TextView, R.color.md_white_1000)
                size1Status = true
            }
            updateSizeTitle()
        }

        size2TextView.setOnClickListener {
            if (size2Status) {
                setSelectUnSelectSizeFilter(size2BgImageView, R.color.md_grey_400, size2TextView, R.color.md_grey_800)
                size2Status = false
            } else {
                setSelectUnSelectSizeFilter(size2BgImageView, R.color.colorPrimary, size2TextView, R.color.md_white_1000)
                size2Status = true
            }
            updateSizeTitle()
        }

        size3TextView.setOnClickListener {
            if (size3Status) {
                setSelectUnSelectSizeFilter(size3BgImageView, R.color.md_grey_400, size3TextView, R.color.md_grey_800)
                size3Status = false
            } else {
                setSelectUnSelectSizeFilter(size3BgImageView, R.color.colorPrimary, size3TextView, R.color.md_white_1000)
                size3Status = true
            }
            updateSizeTitle()
        }

        size4TextView.setOnClickListener {
            if (size4Status) {
                setSelectUnSelectSizeFilter(size4BgImageView, R.color.md_grey_400, size4TextView, R.color.md_grey_800)
                size4Status = false
            } else {
                setSelectUnSelectSizeFilter(size4BgImageView, R.color.colorPrimary, size4TextView, R.color.md_white_1000)
                size4Status = true
            }
            updateSizeTitle()
        }

        size5TextView.setOnClickListener {
            if (size5Status) {
                setSelectUnSelectSizeFilter(size5BgImageView, R.color.md_grey_400, size5TextView, R.color.md_grey_800)
                size5Status = false
            } else {
                setSelectUnSelectSizeFilter(size5BgImageView, R.color.colorPrimary, size5TextView, R.color.md_white_1000)
                size5Status = true
            }
            updateSizeTitle()
        }

        //endregion

        //region Color

        color1ImageView.setOnClickListener {
            if (color1Status) {
                color1ImageView.setImageDrawable(null)
                color1Status = false
            } else {
                color1ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24)
                color1Status = true
            }
            updateColorTitle()
        }

        color2ImageView.setOnClickListener {
            if (color2Status) {
                color2ImageView.setImageDrawable(null)
                color2Status = false
            } else {
                color2ImageView?.setImageResource(R.drawable.baseline_select_with_check_transparent_24)
                color2Status = true
            }
            updateColorTitle()
        }

        color3ImageView.setOnClickListener {
            if (color3Status) {
                color3ImageView.setImageDrawable(null)
                color3Status = false
            } else {
                color3ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24)
                color3Status = true
            }
            updateColorTitle()
        }

        color4ImageView.setOnClickListener {
            if (color4Status) {
                color4ImageView.setImageDrawable(null)
                color4Status = false
            } else {
                color4ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24)
                color4Status = true
            }
            updateColorTitle()
        }

        color5ImageView.setOnClickListener {
            if (color5Status) {
                color5ImageView.setImageDrawable(null)
                color5Status = false
            } else {
                color5ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24)
                color5Status = true
            }
            updateColorTitle()
        }

        color6ImageView.setOnClickListener {
            if (color6Status) {
                color6ImageView.setImageDrawable(null)
                color6Status = false
            } else {
                color6ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24)
                color6Status = true
            }
            updateColorTitle()
        }

        color7ImageView.setOnClickListener {
            if (color7Status) {
                color7ImageView.setImageDrawable(null)
                color7Status = false
            } else {
                color7ImageView.setImageResource(R.drawable.baseline_select_with_check_transparent_24)
                color7Status = true
            }
            updateColorTitle()
        }

        //endregion


    }

    private fun updateColorTitle() {

        var value = 0

        if (color1Status) {
            value++
        }

        if (color2Status) {
            value++
        }

        if (color3Status) {
            value++
        }

        if (color4Status) {
            value++
        }

        if (color5Status) {
            value++
        }

        if (color6Status) {
            value++
        }

        if (color7Status) {
            value++
        }

        val result: String
        if (value == 0) {
            result = "Not Set."
        } else if (value == 1) {
            result = "$value Color"
        } else {
            result = "$value Colors"
        }

        colorTitleValueTextView.text = result

    }

    private fun updateSizeTitle() {
        var value = ""

        if (size1Status) {
            value = size1TextView.text.toString()
        }

        if (size2Status) {
            if (value == "") {
                value += size2TextView.text.toString()
            } else {
                value += ", " + size2TextView.text.toString()
            }
        }

        if (size3Status) {
            if (value == "") {
                value += size3TextView.text.toString()
            } else {
                value += ", " + size3TextView.text.toString()
            }
        }

        if (size4Status) {
            if (value == "") {
                value += size4TextView.text.toString()
            } else {
                value += ", " + size4TextView.text.toString()
            }
        }

        if (size5Status) {
            if (value == "") {
                value += size5TextView.text.toString()
            } else {
                value += ", " + size5TextView.text.toString()
            }
        }

        if (value == "") {
            value = "Not Set."
        }
        sizeTitleValueTextView.text = value
    }

    fun clickMaterial(view: View?) {
        if (view != null && view is Button) {
            view.isSelected = !view.isSelected
        }
    }

    private fun initToolbar() {

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        if (toolbar.navigationIcon != null) {
            toolbar.navigationIcon?.setColorFilter(ContextCompat.getColor(this, R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP)
        }

        toolbar.title = "Expandable 1"

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
}