package com.suifeng.demo.hencoder.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class HistogramView: View {

    private val words = arrayListOf("Froyo", "GB", "ICS", "JB", "KitKat", "L", "M")
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mOffset = 100f    //线的偏移位置
    private val mHistogramOffset = 50f + mOffset   //第一个柱状图的偏移位置
    private val mWidth = 120f    //柱状图间距
    private val mHistogramWidth = 80f  //柱状图宽度
    private val lineStrokeWidth = 2f    //线的宽度
    private val mLineX by lazy {
        context.resources.displayMetrics.widthPixels - mOffset
    }
    private val mMaxHistogramHeight = mLineX - 60 // 柱状图最大高度

    private val mTextSize = 20f

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLACK
        paint.strokeWidth = lineStrokeWidth
        canvas?.drawLine(mOffset, mLineX, mLineX, mLineX, paint)    //横线
        canvas?.drawLine(mOffset, mLineX, mOffset, mOffset, paint) //竖线


        for (i in 0 until words.size) {
            Log.e("wtf", "--->" + words[i])
            //画柱子
            paint.color = Color.parseColor("#303F9F")
            paint.strokeWidth = mHistogramWidth
            canvas?.drawLine(mHistogramOffset + mWidth * i, mLineX, mHistogramOffset + mWidth * i, mMaxHistogramHeight - 100 * i, paint)

            //画字体
            paint.textSize = mTextSize
            val text = words[i]
            val textBound = Rect()
            paint.getTextBounds(text, 0, text.length, textBound)
            canvas?.drawText(text, mHistogramOffset + mWidth * i - textBound.width() / 2, mLineX + 40, paint)
        }

    }
}