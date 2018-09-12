package com.suifeng.demo.hencoder1_1.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class StrokeCapView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.strokeWidth = 50f

        paint.strokeCap = Paint.Cap.BUTT
        canvas?.drawLine(50f, 50f, 400f, 50f, paint)

        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawLine(50f, 150f, 400f, 150f, paint)

        paint.strokeCap = Paint.Cap.SQUARE
        canvas?.drawLine(50f, 250f, 400f, 250f, paint)
    }
}