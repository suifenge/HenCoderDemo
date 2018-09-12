package com.suifeng.demo.hencoder1_2.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder1_2.R

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class XfermodeView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var bitmap1: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)
    private var bitmap2: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman_logo)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.saveLayer(null, null, Canvas.ALL_SAVE_FLAG)

        canvas?.drawBitmap(bitmap1, 0f, 0f, paint)
        val xfermode1 = PorterDuffXfermode(PorterDuff.Mode.SRC)
        paint.xfermode = xfermode1
        canvas?.drawBitmap(bitmap2, 0f, 0f, paint)
        paint.xfermode = null

        canvas?.drawBitmap(bitmap1, bitmap1.width + 40f, 0f, paint)
        val xfermode2 = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        paint.xfermode = xfermode2
        canvas?.drawBitmap(bitmap2, bitmap1.width + 40f, 0f, paint)
        paint.xfermode = null

        canvas?.drawBitmap(bitmap1, 0f, bitmap1.height + 20f, paint)
        val xfermode3 = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        paint.xfermode = xfermode3
        canvas?.drawBitmap(bitmap2, 0f, bitmap1.height + 20f, paint)
        paint.xfermode = null

        canvas?.restore()

    }
}