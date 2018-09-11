package com.suifeng.demo.hencoder.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder.R

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class SimpleDrawView8: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mWidth = 0
    private var mHeight = 0
    private var mBitmap: Bitmap? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.SimpleDrawView8)
        val drawable = attr.getDrawable(R.styleable.SimpleDrawView8_resource) ?: throw Exception("must be image resource！")
        attr.recycle()
        mBitmap = getBitmapFromDrawable(drawable)
        if(mBitmap == null) {
            throw Exception("get bitmap error!")
        }
        mWidth = mBitmap!!.width
        mHeight = mBitmap!!.height
    }

    private fun getBitmapFromDrawable(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight,
                if(drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return bitmap
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measureDimension(mWidth, widthMeasureSpec)
        val height = measureDimension(mHeight, heightMeasureSpec)
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
        canvas?.drawBitmap(mBitmap, 0f, 0f, paint)
    }
}