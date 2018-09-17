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
class MatrixTranslateView: View {

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

        canvas?.save()
        mMatrix.reset()
        mMatrix.postTranslate(-100f, -100f)
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap, point1.x.toFloat(), point1.x.toFloat(), paint)
        canvas?.restore()

        canvas?.save()
        mMatrix.reset()
        mMatrix.postTranslate(100f, 0f)
        canvas?.concat(mMatrix)
        canvas?.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        canvas?.restore()
    }
}