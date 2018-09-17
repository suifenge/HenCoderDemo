package com.suifeng.demo.hencoder1_4.views

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.suifeng.demo.hencoder1_4.R

/**
 * @author ljc
 * @data 2018/9/17
 * @describe
 */
class FlipboardView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)

    private val camera = Camera()
    private var degree = 0
    private val animator = ObjectAnimator.ofInt(this, "degree", 0, 180)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        animator.duration = 2500
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.RESTART
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    fun setDegree(degree: Int) {
        this.degree = degree
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height

        val centerX = width / 2f
        val centerY = height / 2f

        val x = centerX - bitmapWidth / 2f
        val y = centerY - bitmapHeight / 2f

        canvas?.save()
        canvas?.clipRect(0f, 0f, width.toFloat(), centerY)
        canvas?.drawBitmap(bitmap, x, y, paint)
        canvas?.restore()

        canvas?.save()
        if(degree < 90) {
            canvas?.clipRect(0f, centerY, width.toFloat(), height.toFloat())
        } else {
            canvas?.clipRect(0f, 0f, width.toFloat(), centerY)
        }

        camera.save()
        camera.rotateX(degree.toFloat())
        canvas?.translate(centerX, centerY)
        camera.applyToCanvas(canvas)
        canvas?.translate(-centerX, -centerY)
        camera.restore()

        canvas?.drawBitmap(bitmap, x, y, paint)
        canvas?.restore()
    }
}