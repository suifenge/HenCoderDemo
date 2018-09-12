package com.suifeng.demo.hencoder1_2.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.suifeng.demo.hencoder1_2.R

/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class LinearGradientView: View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.LinearGradientView)
        val startColor = attr.getColor(R.styleable.LinearGradientView_startColor, Color.parseColor("#3F51B5"))
        val endColor = attr.getColor(R.styleable.LinearGradientView_endColor, Color.parseColor("#FF4081"))
        val tileMode = attr.getInt(R.styleable.LinearGradientView_tileMode, 0)
        attr.recycle()

        val shader = LinearGradient(100f, 100f, 500f, 500f, startColor,
                endColor, getTileModeEnum(tileMode))
        paint.shader = shader
    }

    private fun getTileModeEnum(tileMode: Int): Shader.TileMode {
        return when(tileMode) {
            1 -> Shader.TileMode.REPEAT
            2 -> Shader.TileMode.MIRROR
            else -> Shader.TileMode.CLAMP
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(300f, 300f, 200f, paint)
    }
}