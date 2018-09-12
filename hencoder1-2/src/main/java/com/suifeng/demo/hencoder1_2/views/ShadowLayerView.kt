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
class ShadowLayerView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.setShadowLayer(10f, 0f, 0f, Color.RED)
        paint.textSize = 120f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText("Hello HenCoder", 50f, 200f, paint)
    }
}