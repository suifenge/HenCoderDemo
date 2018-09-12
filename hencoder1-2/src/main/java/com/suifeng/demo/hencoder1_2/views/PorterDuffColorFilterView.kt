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
class PorterDuffColorFilterView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val porterDuffColorFilter = PorterDuffColorFilter(Color.parseColor("#FF4081"), PorterDuff.Mode.SRC_OVER)
        paint.colorFilter = porterDuffColorFilter
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(bitmap, 100f, 100f, paint)
    }
}