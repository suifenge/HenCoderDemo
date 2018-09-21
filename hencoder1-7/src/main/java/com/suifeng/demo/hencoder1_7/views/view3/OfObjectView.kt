package com.suifeng.demo.hencoder1_7.views.view3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder1_7.Utils

/**
 * @author ljc
 * @data 2018/9/21
 * @describe
 */
class OfObjectView: View {

    private val radius = Utils.dpToPixel(20f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var position = PointF()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.color = Color.parseColor("#009688")
    }

    fun getPosition(): PointF {
        return position
    }

    fun setPosition(position: PointF) {
        this.position = position
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val innerPaddingLeft = radius * 1
        val innerPaddingRight = radius * 1
        val innerPaddingTop = radius * 1
        val innerPaddingBottom = radius * 3
        val width = width - innerPaddingLeft - innerPaddingRight - radius * 2
        val height = height - innerPaddingTop - innerPaddingBottom - radius * 2

        canvas?.drawCircle(innerPaddingLeft + radius + width * position.x, innerPaddingTop + radius + height * position.y, radius, paint)
    }
}