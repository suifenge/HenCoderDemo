package com.suifeng.demo.hencoder1_2.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class FillPathView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pathPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val path = Path()
    private val path1 = Path()
    private val path2 = Path()
    private val path3 = Path()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {

        path.moveTo(50f, 100f)
        path.rLineTo(50f, 100f)
        path.rLineTo(80f, -150f)
        path.rLineTo(100f, 100f)
        path.rLineTo(70f, -120f)
        path.rLineTo(150f, 80f)

        pathPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 0f
        paint.getFillPath(path, path1)
        canvas?.drawPath(path1, paint)

        canvas?.save()
        canvas?.translate(500f, 0f)
        canvas?.drawPath(path1, pathPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(0f, 200f)
        paint.style = Paint.Style.STROKE
        paint.getFillPath(path, path2)
        canvas?.drawPath(path, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(500f, 200f)
        canvas?.drawPath(path2, pathPaint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(0f, 400f)
        paint.strokeWidth = 40f
        paint.getFillPath(path, path3)
        canvas?.drawPath(path, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(500f, 400f)
        canvas?.drawPath(path3, pathPaint)
        canvas?.restore()

    }
}