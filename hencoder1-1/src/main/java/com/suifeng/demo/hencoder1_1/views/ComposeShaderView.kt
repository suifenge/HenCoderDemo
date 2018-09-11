package com.suifeng.demo.hencoder1_1.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder1_1.R

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class ComposeShaderView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val bitmap1 = BitmapFactory.decodeResource(context.resources, R.drawable.batman)
        val shader1 = BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val bitmap2 = BitmapFactory.decodeResource(context.resources, R.drawable.batman_logo)
        val shader2 = BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val shader = ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER)
        paint.shader = shader
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(200f, 200f, 200f, paint)
    }
}