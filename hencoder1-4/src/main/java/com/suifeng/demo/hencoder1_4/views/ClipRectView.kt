package com.suifeng.demo.hencoder1_4.views

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder1_4.R

/**
 * @author ljc
 * @data 2018/9/17
 * @describe
 */
class ClipRectView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val left = (width - bitmap.width) / 2f
        val top = (height - bitmap.height) / 2f

        canvas?.save()
        canvas?.clipRect(left + 50, top + 50, left + 300, top + 200)
        canvas?.drawBitmap(bitmap, left, top, paint)
        canvas?.restore()
    }
}