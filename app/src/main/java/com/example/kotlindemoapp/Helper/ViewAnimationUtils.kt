package com.example.kotlindemoapp.Helper

import androidx.constraintlayout.widget.ConstraintLayout
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

object ViewAnimationUtils {
    fun expand(v: View) {
        v.measure(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight: Int

        if (0 <= v.measuredHeight) {

            targetHeight = 200
        } else {
            targetHeight = v.measuredHeight
        }

        Log.d("TEAMPS", "expand: $targetHeight")

        v.layoutParams.height = 0
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        Log.d("TEAMPS", "expand: " + v.context.resources.displayMetrics.density)
        a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

    fun expandWithoutAnimation(v: View) {
        v.measure(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        val targetHeight: Int

        if (0 <= v.measuredHeight) {

            targetHeight = 200
        } else {
            targetHeight = v.measuredHeight
        }

        Log.d("TEAMPS", "expand: $targetHeight")

        v.layoutParams.height = 0
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        Log.d("TEAMPS", "expand: " + v.context.resources.displayMetrics.density)
        a.duration = 0
        v.startAnimation(a)
    }

    fun collapse(v: View) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        Log.d("TEAMPS", "collapse: " + initialHeight / v.context.resources.displayMetrics.density)
        // 1dp/ms
        a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
        v.startAnimation(a)
    }

    fun collapseWithoutAnimation(v: View) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        Log.d("TEAMPS", "collapse: " + initialHeight / v.context.resources.displayMetrics.density)
        // 1dp/ms
        a.duration = 0
        v.startAnimation(a)
    }
}
