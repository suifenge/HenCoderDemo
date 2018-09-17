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
class CameraRotateFixedView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)

    private val point1 = Point(200, 200)
    private val point2 = Point(600, 200)
    private val camera = Camera()
    private val mMatrix = Matrix()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height

        val center1X = point1.x + bitmapWidth / 2f
        val center1Y = point1.y + bitmapHeight / 2f
        val center2X = point2.x + bitmapWidth / 2f
        val center2Y = point2.y + bitmapHeight / 2f

        camera.save()
        mMatrix.reset()
        camera.rotateX(30f)
        camera.getMatrix(mMatrix)
        camera.restore()
        mMatrix.preTranslate(-center1X, -center1Y)
        mMatrix.postTranslate(center1X, center1Y)
        canvas?.save()
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap, point1.x.toFloat(), point1.x.toFloat(), paint)
        canvas?.restore()

        camera.save()
        mMatrix.reset()
        camera.rotateY(30f)
        camera.getMatrix(mMatrix)
        camera.restore()
        mMatrix.preTranslate(-center2X, -center2Y)
        mMatrix.preTranslate(center2X, center2Y)
        canvas?.save()
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        canvas?.restore()
    }
}