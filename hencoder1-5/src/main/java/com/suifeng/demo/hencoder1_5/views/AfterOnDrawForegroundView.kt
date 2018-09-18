package com.suifeng.demo.hencoder1_5.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.suifeng.demo.hencoder1_5.R

/**
 * @author ljc
 * @data 2018/9/18
 * @describe
 */
class AfterOnDrawForegroundView: AppCompatImageView {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.textSize = 30f
    }

    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)

        paint.color = Color.parseColor("#f44336")
        canvas?.drawRect(0f, 20f, 120f, 60f, paint)
        paint.color = Color.WHITE
        canvas?.drawText("New", 30f, 50f, paint)
    }
}