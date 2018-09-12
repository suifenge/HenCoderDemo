package com.suifeng.demo.hencoder1_3.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class TextSizeView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text = "Hello HenCoder"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var y = 100f
        val x = 50f

        paint.textSize = 16f
        canvas?.drawText(text, x, y, paint)

        y += 30f
        paint.textSize = 24f
        canvas?.drawText(text, x, y, paint)

        y += 55
        paint.textSize = 48f
        canvas?.drawText(text, x, y, paint)

        y += 80
        paint.textSize = 72f
        canvas?.drawText(text, x, y, paint)
    }
}