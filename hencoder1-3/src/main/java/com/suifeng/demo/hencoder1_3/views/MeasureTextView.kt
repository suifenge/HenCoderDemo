package com.suifeng.demo.hencoder1_3.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class MeasureTextView: View {

    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text1 = "三个月内你胖了"
    private val text2 = "4.5"
    private val text3 = "公斤"

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint1.textSize = 60f
        paint2.textSize = 120f
        paint2.color = Color.parseColor("#E91E63")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawText(text1, 50f, 200f, paint1)

        canvas?.drawText(text2, 50f + paint1.measureText(text1), 200f, paint2)

        canvas?.drawText(text3, 50f + paint1.measureText(text1) + paint2.measureText(text2), 200f, paint1)
    }
}