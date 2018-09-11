package com.suifeng.demo.hencoder.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder.R

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class SimpleDrawView1: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG) //抗锯齿
    private var mPaintStyle = 0
    private var mColor = Color.BLACK
    private var mStrokeWidth  = 0f
    private var mRadius = 100f
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        attrs?.let {
            val attr = context.obtainStyledAttributes(attrs, R.styleable.SimpleDrawView1)
            mPaintStyle = attr.getInt(R.styleable.SimpleDrawView1_paintStyle, 0)
            mStrokeWidth = attr.getFloat(R.styleable.SimpleDrawView1_strokeWidth, 0f)
            mColor = attr.getColor(R.styleable.SimpleDrawView1_color, Color.BLACK)
            mRadius = attr.getFloat(R.styleable.SimpleDrawView1_radius, 100f)
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
        val width = measureDimension((mRadius.toInt() + mStrokeWidth.toInt()) *2, widthMeasureSpec)
        val height = measureDimension((mRadius.toInt() + mStrokeWidth.toInt()) *2, heightMeasureSpec)
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(mRadius + mStrokeWidth, mRadius + mStrokeWidth, mRadius, paint)
    }
}