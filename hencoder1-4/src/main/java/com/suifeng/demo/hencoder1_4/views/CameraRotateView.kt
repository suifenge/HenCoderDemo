package com.suifeng.demo.hencoder1_4.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder1_4.R

/**
 * @author ljc
 * @data 2018/9/17
 * @describe
 */
class CameraRotateView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)

    private val point1 = Point(200, 200)
    private val point2 = Point(600, 200)
    private val camera = Camera()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        camera.save()
        camera.rotateX(30f)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas?.drawBitmap(bitmap, point1.x.toFloat(), point1.x.toFloat(), paint)
        canvas?.restore()

        canvas?.save()
        camera.save()
        camera.rotateY(30f)
        camera.applyToCanvas(canvas)
        camera.restore()
        canvas?.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        canvas?.restore()
    }
}