package com.suifeng.demo.hencoder1_2.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder1_2.R

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class MaskFilterView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.what_the_fuck)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.NORMAL)
        canvas?.drawBitmap(bitmap, 100f, 50f, paint)

        paint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.INNER)
        canvas?.drawBitmap(bitmap, bitmap.width + 200f, 50f, paint)

        paint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.OUTER)
        canvas?.drawBitmap(bitmap, 100f, bitmap.height + 100f, paint)

        paint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID)
        canvas?.drawBitmap(bitmap, bitmap.width + 200f, bitmap.height + 100f, paint)

        paint.maskFilter = EmbossMaskFilter(floatArrayOf(0f, 1f, 1f), 0.2f, 8f, 10f)
        canvas?.drawBitmap(bitmap, 100f, bitmap.height * 2f + 200f, paint)

    }
}