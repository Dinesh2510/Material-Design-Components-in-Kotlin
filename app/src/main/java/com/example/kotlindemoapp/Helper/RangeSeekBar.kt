package com.example.kotlindemoapp.Helper


import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.kotlindemoapp.R
import java.math.BigDecimal


class RangeSeekBar<T : Number>
/**
 * Creates a new RangeSeekBar.
 *
 * @param absoluteMinValue The minimum value of the selectable range.
 * @param absoluteMaxValue The maximum value of the selectable range.
 * @param context
 * @throws IllegalArgumentException Will be thrown if min/max value type is not one of Long, Double, Integer, Float, Short, Byte or BigDecimal.
 */
@Throws(IllegalArgumentException::class)
constructor(
    /**
     * Returns the absolute minimum value of the range that has been set at construction time.
     *
     * @return The absolute minimum value of the range.
     */
    val absoluteMinValue: T, private var absoluteMaxValue: T?, context: Context) : androidx.appcompat.widget.AppCompatImageView(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var thumbImage = BitmapFactory.decodeResource(resources, R.drawable.ic_range_bar_bg)
    private val thumbImage2 = BitmapFactory.decodeResource(resources, R.drawable.ic_seek_thumb)
    private val thumbPressedImage: Bitmap//
    private val thumbWidth = thumbImage.width.toFloat()
    private val thumbHalfWidth = 0.5f * thumbWidth
    private val thumbHalfHeight = 0.5f * thumbImage.height
    private val lineHeight = 0.13f * thumbHalfHeight
    private val padding = thumbHalfWidth
    private val numberType: NumberType
    private val absoluteMinValuePrim: Double
    private val absoluteMaxValuePrim: Double
    private var normalizedMinValue = 0.0
    private var normalizedMaxValue = 1.0
    private var pressedThumb: Thumb? = null
    /**
     * Should the widget notify the listener callback while the user is still dragging a thumb? Default is false.
     *
     * @param flag
     */
    var isNotifyWhileDragging = false
    private var listener: OnRangeSeekBarChangeListener<T>? = null

    private var mDownMotionX: Float = 0.toFloat()
    private var mActivePointerId = INVALID_POINTER_ID

    /**
     * On touch, this offset plus the scaled value from the position of the touch will form the progress value. Usually 0.
     */
    internal var mTouchProgressOffset: Float = 0.toFloat()

    private var mScaledTouchSlop: Int = 0
    private var mIsDragging: Boolean = false
    internal var tmp: ImageView? = null

    /**
     * Returns the currently selected min value.
     *
     * @return The currently selected min value.
     */
    /**
     * Sets the currently selected minimum value. The widget will be invalidated and redrawn.
     *
     * @param value The Number value to set the minimum value to. Will be clamped to given absolute minimum/maximum range.
     */
    // in case absoluteMinValue == absoluteMaxValue, avoid division by zero when normalizing.
    var selectedMinValue: T
        get() = normalizedToValue(normalizedMinValue)
        set(value) = if (0.0 == absoluteMaxValuePrim - absoluteMinValuePrim) {
            setNormalizedMinValue(0.0)
        } else {
            setNormalizedMinValue(valueToNormalized(value))
        }

    /**
     * Returns the currently selected max value.
     *
     * @return The currently selected max value.
     */
    /**
     * Sets the currently selected maximum value. The widget will be invalidated and redrawn.
     *
     * @param value The Number value to set the maximum value to. Will be clamped to given absolute minimum/maximum range.
     */
    // in case absoluteMinValue == absoluteMaxValue, avoid division by zero when normalizing.
    var selectedMaxValue: T
        get() = normalizedToValue(normalizedMaxValue)
        set(value) = if (0.0 == absoluteMaxValuePrim - absoluteMinValuePrim) {
            setNormalizedMaxValue(1.0)
        } else {
            setNormalizedMaxValue(valueToNormalized(value))
        }

    init {
        absoluteMinValuePrim = absoluteMinValue.toDouble()
        absoluteMaxValuePrim = absoluteMaxValue!!.toDouble()
        numberType = NumberType.fromNumber(absoluteMinValue)

        // make RangeSeekBar focusable. This solves focus handling issues in case EditText widgets are being used along with the RangeSeekBar within ScollViews.
        isFocusable = true
        isFocusableInTouchMode = true
        init()

        thumbPressedImage = thumbImage.copy(Bitmap.Config.ARGB_8888, true)
        var c: Canvas? = Canvas(thumbPressedImage)
        var p: Paint? = Paint()
        p?.color = ContextCompat.getColor(getContext(), R.color.colorPrimary)
        p?.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(getContext(),R.color.colorPrimary), PorterDuff.Mode.MULTIPLY)
        c?.drawBitmap(thumbPressedImage, 0f, 0f, p)


        thumbImage = thumbImage.copy(Bitmap.Config.ARGB_8888, true)
        var c2: Canvas? = Canvas(thumbImage)
        var p2: Paint? = Paint()
        p2?.color = ContextCompat.getColor(getContext(),R.color.colorAccent)
        p2?.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(getContext(),R.color.colorAccent), PorterDuff.Mode.MULTIPLY)
        c2?.drawBitmap(thumbImage, 0f, 0f, p2)

    }

    private fun init() {
        mScaledTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    /**
     * Returns the absolute maximum value of the range that has been set at construction time.
     *
     * @return The absolute maximum value of the range.
     */
    fun getAbsoluteMaxValue(): T? {
        return absoluteMaxValue
    }

    fun setAbsoluteMaxValue(value: T) {
        absoluteMaxValue = value
        init()
    }

    /**
     * Registers given listener callback to notify about changed selected values.
     *
     * @param listener The listener to notify about changed selected values.
     */
    fun setOnRangeSeekBarChangeListener(listener: OnRangeSeekBarChangeListener<T>) {
        this.listener = listener
    }

    /**
     * Handles thumb selection and movement. Notifies listener callback on certain events.
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (!isEnabled)
            return false

        val pointerIndex: Int

        val action = event.action
        when (action and MotionEvent.ACTION_MASK) {

            MotionEvent.ACTION_DOWN -> {
                // Remember where the motion event started
                mActivePointerId = event.getPointerId(event.pointerCount - 1)
                pointerIndex = event.findPointerIndex(mActivePointerId)
                mDownMotionX = event.getX(pointerIndex)

                pressedThumb = evalPressedThumb(mDownMotionX)

                // Only handle thumb presses.
                if (pressedThumb == null)
                    return super.onTouchEvent(event)

                isPressed = true
                invalidate()
                onStartTrackingTouch()
                trackTouchEvent(event)
                attemptClaimDrag()
            }
            MotionEvent.ACTION_MOVE -> if (pressedThumb != null) {

                if (mIsDragging) {
                    trackTouchEvent(event)
                } else {
                    // Scroll to follow the motion event
                    pointerIndex = event.findPointerIndex(mActivePointerId)
                    val x = event.getX(pointerIndex)

                    if (Math.abs(x - mDownMotionX) > mScaledTouchSlop) {
                        isPressed = true
                        invalidate()
                        onStartTrackingTouch()
                        trackTouchEvent(event)
                        attemptClaimDrag()
                    }
                }

                if (isNotifyWhileDragging && listener != null) {
                    listener?.onRangeSeekBarValuesChanged(this, selectedMinValue, selectedMaxValue)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (mIsDragging) {
                    trackTouchEvent(event)
                    onStopTrackingTouch()
                    setPressed(false)
                } else {
                    // Touch up when we never crossed the touch slop threshold
                    // should be interpreted as a tap-seek to that location.
                    onStartTrackingTouch()
                    trackTouchEvent(event)
                    onStopTrackingTouch()
                }

                pressedThumb = null
                invalidate()
                if (listener != null) {
                    listener?.onRangeSeekBarValuesChanged(this, selectedMinValue, selectedMaxValue)
                }
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                val index = event.pointerCount - 1
                // final int index = ev.getActionIndex();
                mDownMotionX = event.getX(index)
                mActivePointerId = event.getPointerId(index)
                invalidate()
            }
            MotionEvent.ACTION_POINTER_UP -> {
                onSecondaryPointerUp(event)
                invalidate()
            }
            MotionEvent.ACTION_CANCEL -> {
                if (mIsDragging) {
                    onStopTrackingTouch()
                    isPressed = false
                }
                invalidate() // see above explanation
            }
        }
        return true
    }

    private fun onSecondaryPointerUp(ev: MotionEvent) {
        val pointerIndex = ev.action and ACTION_POINTER_INDEX_MASK shr ACTION_POINTER_INDEX_SHIFT

        val pointerId = ev.getPointerId(pointerIndex)
        if (pointerId == mActivePointerId) {
            // This was our active pointer going up. Choose
            // a new active pointer and adjust accordingly.
            // TODO: Make this decision more intelligent.
            val newPointerIndex = if (pointerIndex == 0) 1 else 0
            mDownMotionX = ev.getX(newPointerIndex)
            mActivePointerId = ev.getPointerId(newPointerIndex)
        }
    }

    private fun trackTouchEvent(event: MotionEvent) {
        val pointerIndex = event.findPointerIndex(mActivePointerId)
        val x = event.getX(pointerIndex)

        if (Thumb.MIN == pressedThumb) {
            setNormalizedMinValue(screenToNormalized(x))
        } else if (Thumb.MAX == pressedThumb) {
            setNormalizedMaxValue(screenToNormalized(x))
        }
    }

    /**
     * Tries to claim the user's drag motion, and requests disallowing any ancestors from stealing events in the drag.
     */
    private fun attemptClaimDrag() {
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true)
        }
    }

    /**
     * This is called when the user has started touching this widget.
     */
    internal fun onStartTrackingTouch() {
        mIsDragging = true
    }

    /**
     * This is called when the user either releases his touch or the touch is canceled.
     */
    internal fun onStopTrackingTouch() {
        mIsDragging = false
    }

    /**
     * Ensures correct size of the widget.
     */
    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = 200
        if (View.MeasureSpec.UNSPECIFIED != View.MeasureSpec.getMode(widthMeasureSpec)) {
            width = View.MeasureSpec.getSize(widthMeasureSpec)
        }
        var height = thumbImage.height
        if (View.MeasureSpec.UNSPECIFIED != View.MeasureSpec.getMode(heightMeasureSpec)) {
            height = Math.min(height, View.MeasureSpec.getSize(heightMeasureSpec))
        }
        setMeasuredDimension(width, height)
    }

    /**
     * Draws the widget on the given canvas.
     */
    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // draw seek bar background line
        val rect = RectF(padding, 0.5f * (height - lineHeight), width - padding, 0.5f * (height + lineHeight))
        paint.style = Paint.Style.FILL
        paint.color = Color.GRAY
        paint.isAntiAlias = true
        canvas.drawRect(rect, paint)

        // draw seek bar active range line
        rect.left = normalizedToScreen(normalizedMinValue)
        rect.right = normalizedToScreen(normalizedMaxValue)

        // orange color
        //paint.setColor(DEFAULT_COLOR);
        paint.color = ContextCompat.getColor(context,R.color.colorPrimary)
        canvas.drawRect(rect, paint)

        // draw minimum thumb
        drawThumb(normalizedToScreen(normalizedMinValue), Thumb.MIN == pressedThumb, canvas)

        // draw maximum thumb
        drawThumb(normalizedToScreen(normalizedMaxValue), Thumb.MAX == pressedThumb, canvas)
    }

    /**
     * Overridden to save instance state when device orientation changes. This method is called automatically if you assign an id to the RangeSeekBar widget using the [.setId] method. Other members of this class than the normalized min and max values don't need to be saved.
     */
    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable("SUPER", super.onSaveInstanceState())
        bundle.putDouble("MIN", normalizedMinValue)
        bundle.putDouble("MAX", normalizedMaxValue)
        return bundle
    }

    /**
     * Overridden to restore instance state when device orientation changes. This method is called automatically if you assign an id to the RangeSeekBar widget using the [.setId] method.
     */
    override fun onRestoreInstanceState(parcel: Parcelable) {
        val bundle = parcel as Bundle
        super.onRestoreInstanceState(bundle.getParcelable("SUPER"))
        normalizedMinValue = bundle.getDouble("MIN")
        normalizedMaxValue = bundle.getDouble("MAX")
    }

    /**
     * Draws the "normal" resp. "pressed" thumb image on specified x-coordinate.
     *
     * @param screenCoord The x-coordinate in screen space where to draw the image.
     * @param pressed     Is the thumb currently in "pressed" state?
     * @param canvas      The canvas to draw upon.
     */
    private fun drawThumb(screenCoord: Float, pressed: Boolean, canvas: Canvas) {
        canvas.drawBitmap(if (pressed) thumbImage2 else thumbImage2, screenCoord - thumbHalfWidth, 0.5f * height - thumbHalfHeight, paint)
        canvas.drawBitmap(if (pressed) thumbPressedImage else thumbImage, screenCoord - thumbHalfWidth, 0.5f * height - thumbHalfHeight, paint)
    }

    /**
     * Decides which (if any) thumb is touched by the given x-coordinate.
     *
     * @param touchX The x-coordinate of a touch event in screen space.
     * @return The pressed thumb or null if none has been touched.
     */
    private fun evalPressedThumb(touchX: Float): Thumb? {
        var result: Thumb? = null
        val minThumbPressed = isInThumbRange(touchX, normalizedMinValue)
        val maxThumbPressed = isInThumbRange(touchX, normalizedMaxValue)
        if (minThumbPressed && maxThumbPressed) {
            // if both thumbs are pressed (they lie on top of each other), choose the one with more room to drag. this avoids "stalling" the thumbs in a corner, not being able to drag them apart anymore.
            result = if (touchX / width > 0.5f) Thumb.MIN else Thumb.MAX
        } else if (minThumbPressed) {
            result = Thumb.MIN
        } else if (maxThumbPressed) {
            result = Thumb.MAX
        }
        return result
    }

    /**
     * Decides if given x-coordinate in screen space needs to be interpreted as "within" the normalized thumb x-coordinate.
     *
     * @param touchX               The x-coordinate in screen space to check.
     * @param normalizedThumbValue The normalized x-coordinate of the thumb to check.
     * @return true if x-coordinate is in thumb range, false otherwise.
     */
    private fun isInThumbRange(touchX: Float, normalizedThumbValue: Double): Boolean {
        return Math.abs(touchX - normalizedToScreen(normalizedThumbValue)) <= thumbHalfWidth
    }

    /**
     * Sets normalized min value to value so that 0 <= value <= normalized max value <= 1. The View will get invalidated when calling this method.
     *
     * @param value The new normalized min value to set.
     */
    fun setNormalizedMinValue(value: Double) {
        normalizedMinValue = Math.max(0.0, Math.min(1.0, Math.min(value, normalizedMaxValue)))
        invalidate()
    }

    /**
     * Sets normalized max value to value so that 0 <= normalized min value <= value <= 1. The View will get invalidated when calling this method.
     *
     * @param value The new normalized max value to set.
     */
    fun setNormalizedMaxValue(value: Double) {
        normalizedMaxValue = Math.max(0.0, Math.min(1.0, Math.max(value, normalizedMinValue)))
        invalidate()
    }

    /**
     * Converts a normalized value to a Number object in the value space between absolute minimum and maximum.
     *
     * @param normalized
     * @return
     */
    private fun normalizedToValue(normalized: Double): T {
        return numberType.toNumber(absoluteMinValuePrim + normalized * (absoluteMaxValuePrim - absoluteMinValuePrim)) as T
    }

    /**
     * Converts the given Number value to a normalized double.
     *
     * @param value The Number value to normalize.
     * @return The normalized double.
     */
    private fun valueToNormalized(value: T): Double {
        return if (0.0 == absoluteMaxValuePrim - absoluteMinValuePrim) {
            // prevent division by zero, simply return 0.
            0.0
        } else (value.toDouble() - absoluteMinValuePrim) / (absoluteMaxValuePrim - absoluteMinValuePrim)
    }

    /**
     * Converts a normalized value into screen space.
     *
     * @param normalizedCoord The normalized value to convert.
     * @return The converted value in screen space.
     */
    private fun normalizedToScreen(normalizedCoord: Double): Float {
        return (padding + normalizedCoord * (width - 2 * padding)).toFloat()
    }

    /**
     * Converts screen space x-coordinates into normalized values.
     *
     * @param screenCoord The x-coordinate in screen space to convert.
     * @return The normalized value.
     */
    private fun screenToNormalized(screenCoord: Float): Double {
        val width = width
        if (width <= 2 * padding) {
            // prevent division by zero, simply return 0.
            return 0.0
        } else {
            val result = ((screenCoord - padding) / (width - 2 * padding)).toDouble()
            return Math.min(1.0, Math.max(0.0, result))
        }
    }

    /**
     * Callback listener interface to notify about changed range values.
     *
     * @param <T> The Number type the RangeSeekBar has been declared with.
     * @author Stephan Tittel (stephan.tittel@kom.tu-darmstadt.de)
    </T> */
    interface OnRangeSeekBarChangeListener<T> {
        fun onRangeSeekBarValuesChanged(bar: RangeSeekBar<*>, minValue: T, maxValue: T)
    }

    /**
     * Thumb constants (min and max).
     */
    private enum class Thumb {
        MIN, MAX
    }

    /**
     * Utility enumaration used to convert between Numbers and doubles.
     *
     * @author Stephan Tittel (stephan.tittel@kom.tu-darmstadt.de)
     */
    private enum class NumberType {
        LONG, DOUBLE, INTEGER, FLOAT, SHORT, BYTE, BIG_DECIMAL;

        fun toNumber(value: Double): Number {
            when (this) {
                LONG -> return value.toLong()
                DOUBLE -> return value
                INTEGER -> return value.toInt()
                FLOAT -> return value
                SHORT -> return value.toShort()
                BYTE -> return value.toByte()
                BIG_DECIMAL -> return BigDecimal(value)
            }
        }

        companion object {

            @Throws(IllegalArgumentException::class)
            fun <E : Number> fromNumber(value: E): NumberType {
                if (value is Long) {
                    return LONG
                }
                if (value is Double) {
                    return DOUBLE
                }
                if (value is Int) {
                    return INTEGER
                }
                if (value is Float) {
                    return FLOAT
                }
                if (value is Short) {
                    return SHORT
                }
                if (value is Byte) {
                    return BYTE
                }
                if (value is BigDecimal) {
                    return BIG_DECIMAL
                }
                throw IllegalArgumentException("Number class '" + value.javaClass.name + "' is not supported")
            }
        }
    }

    companion object {

        /**
         * Default color of a , #FF33B5E5. This is also known as "Ice Cream Sandwich" blue.
         */
        val DEFAULT_COLOR = Color.argb(0xFF, 0x33, 0xB5, 0xE5)

        /**
         * An invalid pointer id.
         */
        val INVALID_POINTER_ID = 255

        // Localized constants from MotionEvent for compatibility
        // with API < 8 "Froyo".
        val ACTION_POINTER_UP = 0x6
        val ACTION_POINTER_INDEX_MASK = 0x0000ff00
        val ACTION_POINTER_INDEX_SHIFT = 8
    }
}