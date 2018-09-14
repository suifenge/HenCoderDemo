package com.suifeng.demo.hencoder1_3.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class TextScaleXView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text = "Hello HenCoder"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.color = Color.BLACK
        paint.textSize = 60f
        paint.textScaleX = 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText(text, 50f, 100f, paint)
    }
}