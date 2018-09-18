package com.suifeng.demo.hencoder1_5.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * @author ljc
 * @data 2018/9/17
 * @describe
 */
class BeforeOnDrawView: AppCompatTextView {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.color = Color.parseColor("#FFC107")
    }

    override fun onDraw(canvas: Canvas?) {
        bounds.left = layout.getLineLeft(1)
        bounds.right = layout.getLineRight(1)
        bounds.top = layout.getLineTop(1).toFloat()
        bounds.bottom = layout.getLineBottom(1).toFloat()
        canvas?.drawRect(bounds, paint)
        bounds.left = layout.getLineLeft(layout.lineCount - 4)
        bounds.right = layout.getLineRight(layout.lineCount - 4)
        bounds.top = layout.getLineTop(layout.lineCount - 4).toFloat()
        bounds.bottom = layout.getLineBottom(layout.lineCount - 4).toFloat()
        canvas?.drawRect(bounds, paint)
        super.onDraw(canvas)
    }
}