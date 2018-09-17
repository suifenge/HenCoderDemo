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
class ClipPathView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.maps)

    private val point1 = Point(200, 200)
    private val point2 = Point(600, 200)
    private val path1 = Path()
    private val path2 = Path()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        path1.addCircle(point1.x + 200f, point1.y + 200f, 150f, Path.Direction.CW)

        path2.fillType = Path.FillType.INVERSE_WINDING
        path2.addCircle(point2.x + 200f, point2.y + 200f, 150f, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        canvas?.clipPath(path1)
        canvas?.drawBitmap(bitmap, point1.x.toFloat(), point1.y.toFloat(), paint)
        canvas?.restore()

        canvas?.save()
        canvas?.clipPath(path2)
        canvas?.drawBitmap(bitmap, point2.x.toFloat(), point2.y.toFloat(), paint)
        canvas?.restore()
    }
}