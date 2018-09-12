package com.suifeng.demo.hencoder1_3.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class StaticLayoutView: View {

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val text1 = "Lorem Ipsum is simply dummy text of the printing and typesetting industry."

    private val text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz"

    private val staticLayout1: StaticLayout
    private val staticLayout2: StaticLayout

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        textPaint.color = Color.BLACK
        textPaint.textSize = 60f

        staticLayout1 = StaticLayout(text1, textPaint, 600, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true)

        staticLayout2 = StaticLayout(text2, textPaint, 600, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        canvas?.translate(50f, 100f)
        staticLayout1.draw(canvas)

        canvas?.translate(0f, 400f)
        staticLayout2.draw(canvas)

        canvas?.restore()

    }
}