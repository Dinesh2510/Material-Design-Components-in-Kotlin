@file:Suppress("NAME_SHADOWING")

package com.example.kotlindemoapp.Helper

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.IOException
import java.io.InputStream
import java.util.*


object Utils {

    val isRTL: Boolean
        get() = isRTL(Locale.getDefault())

    fun inputStreamToString(inputStream: InputStream): String? {
        try {
            val bytes = ByteArray(inputStream.available())

            inputStream.read(bytes, 0, bytes.size)

            return String(bytes)
        } catch (e: IOException) {
            return null
        }

    }

    fun toggleUpDownWithAnimation(view: View): Boolean {
        if (view.rotation == 0f) {
            view.animate().setDuration(150).rotation(180f)
            return true
        } else {
            view.animate().setDuration(150).rotation(0f)
            return false
        }
    }

    fun toggleUpDownWithAnimation(view: View, duration: Int, degree: Int) {

        view.animate().setDuration(duration.toLong()).rotation(degree.toFloat())

    }

    /**
     * Function to convert milliseconds time to
     * Timer Format
     * Hours:Minutes:Seconds
     */
    fun milliSecondsToTimer(milliseconds: Long): String {
        var finalTimerString = ""
        val secondsString: String

        // Convert total duration into time
        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
        // Add hours if there
        if (hours > 0) {
            finalTimerString = "$hours:"
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0$seconds"
        } else {
            secondsString = "" + seconds
        }

        finalTimerString = "$finalTimerString$minutes:$secondsString"

        // return timer string
        return finalTimerString
    }

    /**
     * Function to get Progress percentage
     */
    fun getProgressPercentage(currentDuration: Long, totalDuration: Long): Int {
        val percentage: Double?

        val currentSeconds = (currentDuration / 1000).toInt().toLong()
        val totalSeconds = (totalDuration / 1000).toInt().toLong()

        // calculating percentage
        percentage = currentSeconds.toDouble() / totalSeconds * 100

        // return percentage
        return percentage.toInt()
    }

    /**
     * Function to change progress to timer
     *
     * @param progress      -
     * @param totalDuration returns current duration in milliseconds
     */
    fun progressToTimer(progress: Int, totalDuration: Int): Int {
        var totalDuration = totalDuration
        val currentDuration: Int
        totalDuration = totalDuration / 1000
        currentDuration = (progress.toDouble() / 100 * totalDuration).toInt()

        // return current duration in milliseconds
        return currentDuration * 1000
    }

    fun getDrawableInt(context: Context?, name: String?): Int {
        return context?.resources!!.getIdentifier(name, "drawable", context.packageName)
    }

    fun getResourceInt(context: Context?, defType: String, name: String): Int {
        return context?.resources!!.getIdentifier(name, defType, context.packageName)
    }

    fun setImageToImageView(context: Context, imageView: ImageView, drawable: Int) {
        val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                .skipMemoryCache(true)

        Glide.with(context)
                .load(drawable)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }

    fun setCircleImageToImageView(context: Context?, imageView: ImageView, drawable: Int, borderWidth: Int, color: Int) {
        val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                .skipMemoryCache(true)
                .circleCrop()

        if (borderWidth > 0) {
            Glide.with(context!!)
                    .load(drawable)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {

                            imageView.setImageDrawable(resource)

                            try {
                                val colorContextCompat = ContextCompat.getColor(context, color)


                                val bitmap = (resource as BitmapDrawable).bitmap

                                if (bitmap != null) {

                                    val d = BitmapDrawable(context.resources, getCircularBitmapWithBorder(bitmap, borderWidth, colorContextCompat))

                                    imageView.setImageDrawable(d)
                                } else {
                                    imageView.setImageDrawable(resource)
                                }
                            } catch (e: Exception) {
                                Log.e("TEAMPS", "onResourceReady: ", e)
                            }

                        }
                    })
        } else {
            Glide.with(context!!)
                    .load(drawable)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
        }
    }

    fun setCircleImageToImageView(context: Context, imageView: ImageView, drawable: Int, drawableTintColor: Int, borderWidth: Int, color: Int) {
        val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                .skipMemoryCache(true)
                .circleCrop()

        if (borderWidth > 0) {
            Glide.with(context)
                    .load(drawable)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {

                            imageView.setImageDrawable(resource)

                            try {
                                val colorContextCompat = ContextCompat.getColor(context, color)

                                var bitmap: Bitmap? = (resource as BitmapDrawable).bitmap

                                if (bitmap != null) {

                                    val paint = Paint()
                                    paint.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context,drawableTintColor), PorterDuff.Mode.SRC_IN)
                                    val bitmapResult = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)

                                    val canvas = Canvas(bitmapResult)
                                    canvas.drawBitmap(bitmap, 0f, 0f, paint)

                                    bitmap = bitmapResult

                                    val d = BitmapDrawable(context.resources, getCircularBitmapWithBorder(bitmap, borderWidth, colorContextCompat))

                                    imageView.setImageDrawable(d)
                                } else {
                                    imageView.setImageDrawable(resource)
                                }
                            } catch (e: Exception) {
                                Log.e("TEAMPS", "onResourceReady: ", e)
                            }

                        }
                    })
        } else {
            Glide.with(context)
                    .load(drawable)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
        }
    }

    fun setCornerRadiusImageToImageView(context: Context, imageView: ImageView, drawable: Int, radius: Int, borderWidth: Int, color: Int) {
        var requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                .override(500, 500)
                .centerCrop()
                .skipMemoryCache(true)
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(radius))

        if (borderWidth > 0) {
            Glide.with(context)
                    .load(drawable)

                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(object : SimpleTarget<Drawable>() {
                        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {

                            imageView.setImageDrawable(resource)

                            try {
                                val colorContextCompat = ContextCompat.getColor(context, color)

                                val bitmap = (imageView.drawable as BitmapDrawable).bitmap

                                if (bitmap != null) {
                                    val d = BitmapDrawable(context.resources, getRoundedCornerBitmap(context, bitmap, colorContextCompat, radius, borderWidth))
                                    imageView.setImageDrawable(d)
                                } else {
                                    imageView.setImageDrawable(resource)
                                }
                            } catch (e: Exception) {
                                Log.e("TEAMPS", "onResourceReady: ", e)
                            }

                        }
                    })
        } else {
            Glide.with(context)
                    .load(drawable)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
        }
    }

    fun getCircularBitmapWithBorder(bitmap: Bitmap?,
                                    borderWidth: Int, color: Int): Bitmap? {
        if (bitmap == null || bitmap.isRecycled) {
            return null
        }

        val width = bitmap.width + borderWidth
        val height = bitmap.height + borderWidth

        val canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = shader

        val canvas = Canvas(canvasBitmap)
        val radius = if (width > height) height.toFloat() / 2f else width.toFloat() / 2f
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
        paint.shader = null
        paint.style = Paint.Style.STROKE
        paint.color = color
        paint.strokeWidth = borderWidth.toFloat()
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius - borderWidth / 2, paint)
        return canvasBitmap
    }

    private fun getRoundedCornerBitmap(context: Context, bitmap: Bitmap, color: Int, cornerDips: Int, borderDips: Int): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        val borderSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, borderDips.toFloat(),
                context.resources.displayMetrics).toInt()
        val cornerSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, cornerDips.toFloat(),
                context.resources.displayMetrics).toInt()
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)

        // prepare canvas for transfer
        paint.isAntiAlias = true
        paint.color = -0x1
        paint.style = Paint.Style.FILL
        canvas.drawARGB(0, 0, 0, 0)
        canvas.drawRoundRect(rectF, cornerSizePx.toFloat(), cornerSizePx.toFloat(), paint)

        // draw bitmap
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
        canvas.drawBitmap(bitmap, rect, rect, paint)

        // draw border
        paint.color = color
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderSizePx.toFloat()
        canvas.drawRoundRect(rectF, cornerSizePx.toFloat(), cornerSizePx.toFloat(), paint)

        return output
    }


    @SuppressLint("RestrictedApi")
    fun removeShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShifting(false)
                // set once again checked value, so view will be updated
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field")
        } catch (e: IllegalAccessException) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode")
        }

    }

    private fun isRTL(locale: Locale): Boolean {
        val directionality = Character.getDirectionality(locale.displayName[0]).toInt()
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT.toInt() || directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC.toInt()
    }

    fun pxToDp(context: Context, px: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun dpToPx(context: Context, dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun updateMenuIconColor(menu: Menu, @ColorInt color: Int) {
        for (index in 0 until menu.size()) {
            val drawable = menu.getItem(index).icon
            drawable?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    fun hideFirstFab(v: View) {
        v.visibility = View.GONE
        v.translationY = v.height.toFloat()
        v.alpha = 0f
    }

    fun twistFab(v: View, rotate: Boolean): Boolean {
        v.animate().setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                    }
                })
                .rotation(if (rotate) 165f else 0f)
        return rotate
    }

    fun showFab(v: View) {
        v.visibility = View.VISIBLE
        v.alpha = 0f
        v.translationY = v.height.toFloat()
        v.animate()
                .setDuration(300)
                .translationY(0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                    }
                })
                .alpha(1f)
                .start()
    }

    fun hideFab(v: View) {
        v.visibility = View.VISIBLE
        v.alpha = 1f
        v.translationY = 0f
        v.animate()
                .setDuration(300)
                .translationY(v.height.toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        v.visibility = View.GONE
                        super.onAnimationEnd(animation)
                    }
                }).alpha(0f)
                .start()
    }

}
