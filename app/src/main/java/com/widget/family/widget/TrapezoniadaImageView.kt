package com.widget.family.widget

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.widget.family.R

/**
 * Created by wei.
 * Date: 2019-04-27 16:07
 * Description:
 */
class TrapezoniadaImageView : AppCompatImageView {

    private val clipPath by lazy { Path() }
    private val borderPath by lazy { Path() }
    private val borderPaint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val clickRegion by lazy { Region() }
    private val clickRect by lazy { RectF() }

    var start = NONE
    var end = NONE
    var distance = 0f

    var borderEnabled = true
    var borderSize = 10f
    var borderColor = Color.BLACK

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.TrapezoniadaImageViewJ)

            with(a) {
                start = getInt(R.styleable.TrapezoniadaImageViewJ_di_start, NONE)
                end = getInt(R.styleable.TrapezoniadaImageViewJ_di_end, NONE)
                distance = getDimensionPixelSize(R.styleable.TrapezoniadaImageViewJ_di_distance, 0).toFloat()
                borderEnabled = getBoolean(R.styleable.TrapezoniadaImageViewJ_di_borderEnabled, true)
                borderSize = getDimensionPixelSize(R.styleable.TrapezoniadaImageViewJ_di_borderSize, 10).toFloat()
                borderColor = getColor(R.styleable.TrapezoniadaImageViewJ_di_borderColor, Color.BLACK)
                recycle()
            }

            borderPaint.apply {
                style = Paint.Style.STROKE
                color = borderColor
                strokeWidth = borderSize
            }

            // refer this https://developer.android.com/guide/topics/graphics/hardware-accel.html#unsupported
            val layerType =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) LAYER_TYPE_HARDWARE else LAYER_TYPE_SOFTWARE
            setLayerType(layerType, null)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (clipPath.isEmpty) {
            super.onDraw(canvas)
            return
        }
        canvas?.apply {
            val lastSave = save()
            clipPath(clipPath)
            super.onDraw(this)
            // draw border
            borderPath.takeUnless { it.isEmpty }
                    ?.run {
                        drawPath(this, borderPaint)
                    }
            restoreToCount(lastSave)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            setClipPath()
        }
    }

    override fun invalidate() {
        super.invalidate()
        setClipPath()
    }

    private fun setClipPath() {
        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()

        if (width <= 0 || height <= 0) {
            return
        }

        clipPath.reset()
        borderPath.reset()

        when (start) {
            RIGHT -> {

                    clipPath.apply {

                        moveTo(0f,0f)
                        lineTo(width,0f)
                        lineTo(width-distance,height)
                        lineTo(0f,height)


//                        moveTo(0f, 0f)
//                        lineTo(width - distance, 0f)
//                        lineTo(width, height)
//                        lineTo(0f, height)
                    }

                    borderPath.takeIf { borderEnabled }
                            ?.apply {
                                moveTo(width,0f)
                                lineTo(width-distance,height)
//                                moveTo(width - distance, 0f)
//                                lineTo(width, height)
                            }
                }
//            }
            else -> return
        }

        clipPath.close()
        borderPath.close()

        clipPath.computeBounds(clickRect, true)
        val region = Region(
                clickRect.left.toInt(),
                clickRect.top.toInt(),
                clickRect.right.toInt(),
                clickRect.bottom.toInt()
        )
        clickRegion.setPath(clipPath, region)
    }

    companion object {
        const val NONE = 0
//        const val LEFT = 1
//        const val TOP = 2
        const val RIGHT = 3
        const val BOTTOM = 4
    }
}