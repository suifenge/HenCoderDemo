package com.suifeng.demo.hencoder1_2.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class TextPathView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pathPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val textPath = Path()
    private val text = "Hello HenCoder"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.textSize = 120f
        paint.getTextPath(text, 0, text.length, 50f, 400f, textPath)
        pathPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText(text, 50f, 200f, paint)

        canvas?.drawPath(textPath, pathPaint)
    }
}