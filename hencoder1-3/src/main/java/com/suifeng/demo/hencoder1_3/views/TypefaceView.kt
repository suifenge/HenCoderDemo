package com.suifeng.demo.hencoder1_3.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class TypefaceView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text = "Hello HenCoder"
    private val typeface: Typeface

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.color = Color.BLACK
        paint.textSize = 60f
        typeface = Typeface.createFromAsset(context.assets, "Satisfy-Regular.ttf")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawText(text, 50f, 100f, paint)

        paint.typeface = Typeface.SERIF
        canvas?.drawText(text, 50f, 200f, paint)

        paint.typeface = typeface
        canvas?.drawText(text, 50f, 300f, paint)
    }
}