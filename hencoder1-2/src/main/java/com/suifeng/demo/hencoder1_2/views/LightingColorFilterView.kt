package com.suifeng.demo.hencoder1_2.views

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
class LightingColorFilterView: View {

    private val paint1 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)

    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val lightColorFilter1 = LightingColorFilter(0x00ffff, 0x000000)
        paint1.colorFilter = lightColorFilter1

        val lightingColorFilter2 = LightingColorFilter(0xffffff, 0x003000)
        paint2.colorFilter = lightingColorFilter2
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //移除红色
        canvas?.drawBitmap(bitmap, 0f, 0f, paint1)

        //增强绿色
        canvas?.drawBitmap(bitmap, bitmap.width + 100f, 0f, paint2)
    }
}