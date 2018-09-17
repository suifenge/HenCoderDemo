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
class CameraRotateHittingFaceView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)

    private val point = Point(200, 50)

    private val camera = Camera()
    private val mMatrix = Matrix()

    private var degree = 0
    private val animator = ObjectAnimator.ofInt(this, "degree", 0, 360)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val scaleBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width * 2, bitmap.height * 2, true)
        bitmap.recycle()
        bitmap = scaleBitmap

        animator.duration = 5000
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = ValueAnimator.INFINITE

        val displayMetrics = resources.displayMetrics
        val newZ = - displayMetrics.density * 6
        camera.setLocation(0f, 0f, newZ)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.end()
    }

    @SuppressWarnings("unused")
    fun setDegree(degree: Int) {
        this.degree = degree
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height

        val centerX = point.x + bitmapWidth / 2f
        val centerY = point.y + bitmapHeight / 2f

        camera.save()
        mMatrix.reset()
        camera.rotateX(degree.toFloat())
        camera.getMatrix(mMatrix)
        camera.restore()
        mMatrix.preTranslate(-centerX, -centerY)
        mMatrix.preTranslate(centerX, centerY)
        canvas?.save()
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap, point.x.toFloat(), point.y.toFloat(), paint)
        canvas?.restore()
    }
}