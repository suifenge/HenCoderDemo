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
class MatrixRotateView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)

    private val point1 = Point(200, 200)
    private val point2 = Point(600, 200)
    private val mMatrix = Matrix()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val bitmapWidth = bitmap.width
        val bitmapHeight= bitmap.height

        canvas?.save()
        mMatrix.reset()
        mMatrix.postRotate(180f, point1.x + bitmapWidth / 2f, point1.y + bitmapHeight / 2f)
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap, point1.x.toFloat(), point1.x.toFloat(), paint)
        canvas?.restore()

        canvas?.save()
        mMatrix.reset()
        mMatrix.postRotate(45f, point2.x + bitmapWidth / 2f, point2.y + bitmapHeight / 2f)
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        canvas?.restore()
    }
}