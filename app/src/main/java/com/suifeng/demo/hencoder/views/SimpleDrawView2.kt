package com.suifeng.demo.hencoder.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder.R

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class SimpleDrawView2: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG) //抗锯齿
    private var mPaintStyle = 0
    private var mColor = Color.BLACK
    private var mStrokeWidth  = 0f
    private var mWidth = 200f
    private var mHeight = mWidth
    private var mIsRoud = false
    private var mRx = 20f
    private var mRy = mRx
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        attrs?.let {
            val attr = context.obtainStyledAttributes(attrs, R.styleable.SimpleDrawView2)
            mPaintStyle = attr.getInt(R.styleable.SimpleDrawView2_paintStyle, 0)
            mStrokeWidth = attr.getFloat(R.styleable.SimpleDrawView2_strokeWidth, 0f)
            mColor = attr.getColor(R.styleable.SimpleDrawView2_color, Color.BLACK)
            mWidth = attr.getFloat(R.styleable.SimpleDrawView2_mWidth, 200f)
            mHeight = attr.getFloat(R.styleable.SimpleDrawView2_mHeight, 200f)
            mIsRoud = attr.getBoolean(R.styleable.SimpleDrawView2_round, false)
            mRx = attr.getFloat(R.styleable.SimpleDrawView2_rx, 20f)
            mRy = attr.getFloat(R.styleable.SimpleDrawView2_ry, 20f)
            attr.recycle()
        }
        when(mPaintStyle) {
            0 -> {
                paint.style = Paint.Style.FILL
            }
            1 -> {
                paint.style = Paint.Style.STROKE
                paint.strokeWidth = mStrokeWidth
            }
            2 -> {
                paint.style = Paint.Style.FILL_AND_STROKE
                paint.strokeWidth = mStrokeWidth
            }
        }
        paint.color = mColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measureDimension((mWidth.toInt() + mStrokeWidth.toInt()), widthMeasureSpec)
        val height = measureDimension((mHeight.toInt() + mStrokeWidth.toInt()), heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    private fun measureDimension(defaultSize: Int, measureSpec: Int): Int {
        var result: Int

        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = defaultSize   //UNSPECIFIED
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(mIsRoud) {
            canvas?.drawRoundRect(0f, 0f, mWidth + mStrokeWidth, mHeight + mStrokeWidth, mRx, mRy, paint)
        } else {
            canvas?.drawRect(0f, 0f, mWidth + mStrokeWidth, mHeight + mStrokeWidth, paint)
        }
    }
}