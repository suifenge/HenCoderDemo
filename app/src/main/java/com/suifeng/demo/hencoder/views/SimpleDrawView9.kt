package com.suifeng.demo.hencoder.views

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
class SimpleDrawView9: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text = "Hello HenCoder"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.color = Color.parseColor("#000000")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.textSize = 18f
        canvas?.drawText(text, 100f, 25f, paint)

        paint.textSize = 36f
        canvas?.drawText(text, 100f, 70f, paint)

        paint.textSize = 60f
        canvas?.drawText(text, 100f, 145f, paint)

        paint.textSize = 84f
        canvas?.drawText(text, 100f, 240f, paint)
    }
}