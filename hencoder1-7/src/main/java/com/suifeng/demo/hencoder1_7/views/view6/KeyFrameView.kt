package com.suifeng.demo.hencoder1_7.views.view6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder1_7.Utils

/**
 * @author ljc
 * @data 2018/9/21
 * @describe
 */
class KeyFrameView: View {

    private val radius = Utils .dpToPixel(80f)

    private var progress = 0f

    private val arcRectF = RectF()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        paint.textSize = Utils.dpToPixel(40f)
        paint.textAlign = Paint.Align.CENTER
    }

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f

        paint.color = Color.parseColor("#E91E63")
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = Utils.dpToPixel(15f)
        arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        canvas?.drawArc(arcRectF, 135f, progress * 2.7f, false, paint)

        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        canvas?.drawText("${progress.toInt()}%", centerX, centerY - (paint.ascent() + paint.descent()) / 2, paint)
    }

}