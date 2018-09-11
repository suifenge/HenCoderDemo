package com.suifeng.demo.hencoder.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class PieChartView: View {

    private val mPositionX = 300f
    private val mPositionY = 300f
    private val mWidth = 300f
    private val mGap = 2f
    private val angles = floatArrayOf(20f, 10f, 45f, 105f, 120f, 60f)
    private val colors = arrayListOf("#FF4500", "#BCEE68", "#5CACEE", "#FFA500", "#D15FEE", "#FF4081")
    private val words = arrayListOf("GB", "ICS", "JB", "KitKat", "L", "M")
    private val mCommonRect = RectF(-mPositionX, -mPositionY, mWidth, mWidth)

    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG) //画字
    private val paintLine = Paint(Paint.ANTI_ALIAS_FLAG) //画线

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paintLine.strokeWidth = 5f
        paintLine.color = Color.LTGRAY

        paintText.textSize = 30f
        paintText.color - Color.LTGRAY
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.translate(width /2f, height/2f) //饼图居中

        var startAngle = 0f
        var sweepAngle: Float

        for (i in 0 until words.size) {

            paint.strokeWidth = 10f
            paint.color = Color.parseColor(colors[i])
            sweepAngle = angles[i]
            val text = words[i]
            val textAngle = startAngle + angles[i] / 2

            //画饼子
            canvas?.drawArc(mCommonRect, startAngle + mGap, sweepAngle - mGap, true, paint)

            val pointY = (Math.sin(textAngle * Math.PI / 180) * 300).toFloat()
            val pointX = (Math.cos(textAngle * Math.PI / 180) * 300).toFloat()

            val lineY = (Math.sin(textAngle * Math.PI / 180) * 350).toFloat()
            val lineX = (Math.cos(textAngle * Math.PI / 180) * 350).toFloat()

            canvas?.drawLine(pointX, pointY, lineX, lineY, paintLine)

            if(lineX < 0) {
                val textRect = getTextRect(text, paintText)
                canvas?.drawLine(lineX, lineY, -400f, lineY, paintLine)
                canvas?.drawText(text, -420f - textRect.width(), lineY, paintText)
            } else {
                canvas?.drawLine(lineX, lineY, 400f, lineY, paintLine)
                canvas?.drawText(text, 400f, lineY, paintText)
            }

            startAngle += sweepAngle
        }
    }

    private fun getTextRect(text: String, paint: Paint): Rect {
        val mBound = Rect()
        paint.getTextBounds(text, 0, text.length, mBound)
        return mBound
    }
}