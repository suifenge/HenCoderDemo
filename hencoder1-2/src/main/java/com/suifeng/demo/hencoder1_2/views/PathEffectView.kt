package com.suifeng.demo.hencoder1_2.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class PathEffectView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.style = Paint.Style.STROKE
        path.moveTo(50f, 100f)
        path.rLineTo(50f, 100f)
        path.rLineTo(80f, -150f)
        path.rLineTo(100f, 100f)
        path.rLineTo(70f, 120f)
        path.rLineTo(150f, 80f)
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.pathEffect = CornerPathEffect(10f)
        canvas?.drawPath(path, paint)

        canvas?.save()
        canvas?.translate(500f, 0f)
        paint.pathEffect = DiscretePathEffect(20f, 5f)
        canvas?.drawPath(path, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(0f, 200f)
        paint.pathEffect = DashPathEffect(floatArrayOf(10f, 5f), 10f)
        canvas?.drawPath(path, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(500f, 200f)
        val dashPath = Path()
        dashPath.moveTo(0f, 20f)
        dashPath.lineTo(20f, 0f)
        dashPath.lineTo(40f, 20f)
        dashPath.close()
        paint.pathEffect = PathDashPathEffect(dashPath, 40f, 0f, PathDashPathEffect.Style.TRANSLATE)
        canvas?.drawPath(path, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(0f, 400f)
        val dashEffect = DashPathEffect(floatArrayOf(20f, 10f), 0f)
        val discreteEffect = DiscretePathEffect(20f, 5f)
        paint.pathEffect = SumPathEffect(dashEffect, discreteEffect)
        canvas?.drawPath(path, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(500f, 400f)
        paint.pathEffect = ComposePathEffect(dashEffect, discreteEffect)
        canvas?.drawPath(path, paint)
        canvas?.restore()

    }
}