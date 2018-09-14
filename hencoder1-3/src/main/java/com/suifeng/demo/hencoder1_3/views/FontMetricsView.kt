package com.suifeng.demo.hencoder1_3.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class FontMetricsView: View {

    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val texts = arrayListOf("A", "a", "J", "j", "Â", "â")
    private var yOffsets: Float = 0f
    private var top = 200f
    private var bottom = 400f

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint1.style = Paint.Style.STROKE
        paint1.strokeWidth = 20f
        paint1.color = Color.parseColor("#E91E63")
        paint2.textSize = 160f

        val fontMetrics = paint2.fontMetrics
        yOffsets = - (fontMetrics.ascent + fontMetrics.descent) / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(50f, top, width - 50f, bottom, paint1)

        val middle = (top + bottom) / 2f

        canvas?.drawText(texts[0], 100f, middle + yOffsets, paint2)
        canvas?.drawText(texts[1], 200f, middle + yOffsets, paint2)
        canvas?.drawText(texts[2], 300f, middle + yOffsets, paint2)
        canvas?.drawText(texts[3], 400f, middle + yOffsets, paint2)
        canvas?.drawText(texts[4], 500f, middle + yOffsets, paint2)
        canvas?.drawText(texts[5], 600f, middle+ yOffsets, paint2)
    }
}