package com.suifeng.demo.hencoder1_5.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * @author ljc
 * @data 2018/9/18
 * @describe
 */
class DispatchDrawLayout: LinearLayout {

    private val pattern = Pattern()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        setWillNotDraw(false)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        pattern.draw(canvas)
    }

    private inner class Pattern constructor(){

        private val PATTERN_RATIO = 5f / 6

        private val patternPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        private var spots: ArrayList<Spot>

        init {
            spots = ArrayList()
            spots.add(Spot(0.24f, 0.3f, 0.026f))
            spots.add(Spot(0.51f, 0.25f, 0.067f))
            spots.add(Spot(0.22f, 0.6f, 0.067f))
            spots.add(Spot(0.52f, 0.78f, 0.083f))

            patternPaint.color = Color.parseColor("#A0E91E63")
        }

        constructor(spots: ArrayList<Spot>) : this() {
            this.spots = spots
        }

        fun draw(canvas: Canvas?) {
            val repitition = Math.ceil(width / height.toDouble()).toInt()
            for (i in 0 until spots.size * repitition) {
                val spot = spots[i % spots.size]
                canvas?.drawCircle(i / spots.size * height * PATTERN_RATIO + spot.relativeX * height,
                        spot.relativeY * height, spot.relativeSize * height, patternPaint)
            }
        }

        private inner class Spot constructor(var relativeX: Float, var relativeY: Float, var relativeSize: Float)
    }
}