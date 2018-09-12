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
class RadialGradientView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val shader = RadialGradient(300f, 300f, 200f, Color.parseColor("#FF4081"),
                Color.parseColor("#3F51B5"), Shader.TileMode.CLAMP)
        paint.shader = shader
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(300f, 300f, 200f, paint)
    }
}