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
class ColorMatrixColorFilterView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman_logo)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f) // 0f - 1f
        val colorMatrixColorFilter = ColorMatrixColorFilter(colorMatrix)
        paint.colorFilter = colorMatrixColorFilter
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, 100f, 100f, paint)
    }
}