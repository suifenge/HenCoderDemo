package com.suifeng.demo.hencoder.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class SimpleDrawView5: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.parseColor("#3F51B5")
        paint.style = Paint.Style.FILL
        canvas?.drawArc(200f, 100f, 800f, 500f, -110f, 100f, true, paint)
        canvas?.drawArc(200f, 100f, 800f, 500f, 0f, 180f, false, paint)
        paint.style = Paint.Style.STROKE
        canvas?.drawArc(200f, 100f, 800f, 500f, 180f, 70f, true, paint)
    }
}