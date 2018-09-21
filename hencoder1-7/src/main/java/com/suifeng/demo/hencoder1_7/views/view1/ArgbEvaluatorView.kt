package com.suifeng.demo.hencoder1_7.views.view1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/21
 * @describe
 */
class ArgbEvaluatorView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var color = Color.parseColor("#ffff0000")

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    fun getColor(): Int {
        return color
    }

    fun setColor(color: Int) {
        this.color = color
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = color
        canvas?.drawCircle(width / 2f, height / 2f, width / 6f, paint)
    }
}