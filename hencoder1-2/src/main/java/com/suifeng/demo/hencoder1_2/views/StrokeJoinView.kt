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
class StrokeJoinView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.strokeWidth = 40f
        paint.style = Paint.Style.STROKE

        path.rLineTo(200f, 0f)
        path.rLineTo(-160f, 120f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()

        canvas?.translate(100f, 100f)
        paint.strokeJoin = Paint.Join.MITER
        canvas?.drawPath(path, paint)

        canvas?.translate(300f, 0f)
        paint.strokeJoin = Paint.Join.BEVEL
        canvas?.drawPath(path, paint)

        canvas?.translate(300f, 0f)
        paint.strokeJoin = Paint.Join.ROUND
        canvas?.drawPath(path, paint)

        canvas?.restore()
    }
}