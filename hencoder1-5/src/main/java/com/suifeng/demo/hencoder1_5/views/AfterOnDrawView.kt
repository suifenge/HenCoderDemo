package com.suifeng.demo.hencoder1_5.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.suifeng.demo.hencoder1_5.R

/**
 * @author ljc
 * @data 2018/9/17
 * @describe
 */
class AfterOnDrawView: AppCompatImageView {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    companion object {
        const val DEBUG = true
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.color = Color.parseColor("#FFC107")
        paint.textSize = 28f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(DEBUG) {

            if(drawable != null) {
                canvas?.save()
                canvas?.concat(imageMatrix)
                val bounds = drawable.bounds
                canvas?.drawText(resources.getString(R.string.image_size, bounds.width(), bounds.height()), 20f, 40f, paint)
                canvas?.restore()
            }

        }
    }
}