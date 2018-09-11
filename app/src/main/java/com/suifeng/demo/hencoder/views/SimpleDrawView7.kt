package com.suifeng.demo.hencoder.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class SimpleDrawView7: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.color = Color.parseColor("#FF4081")
        path.addArc(200f, 200f, 400f, 400f, -225f, 225f)
        path.arcTo(400f, 200f, 600f, 400f, -180f, 225f, false)
        path.lineTo(400f, 542f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }
}