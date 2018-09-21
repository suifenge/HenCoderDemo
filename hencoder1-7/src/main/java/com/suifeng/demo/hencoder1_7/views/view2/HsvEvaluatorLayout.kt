package com.suifeng.demo.hencoder1_7.views.view2

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.RelativeLayout
import com.suifeng.demo.hencoder1_7.R

/**
 * @author ljc
 * @data 2018/9/21
 * @describe
 */
class HsvEvaluatorLayout: RelativeLayout {

    lateinit var view: HsvEvaluatorView
    lateinit var animateBtn: Button

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        view = findViewById(R.id.animator_view)
        animateBtn = findViewById(R.id.animate_btn)

        val animator = ObjectAnimator.ofInt(view, "color", Color.parseColor("#ffff0000"), Color.parseColor("#ff00ff00"))
        animator.setEvaluator(HsvEvaluator())
        animator.interpolator = LinearInterpolator()
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE

        animateBtn.setOnClickListener {
            if(animator.isStarted) return@setOnClickListener
            animator.start()
        }
    }

    private class HsvEvaluator: TypeEvaluator<Int> {

        private val startHsv = floatArrayOf(0f, 0f, 0f)
        private val endHsv = floatArrayOf(0f, 0f, 0f)
        private val outHsv = floatArrayOf(0f, 0f, 0f)

        override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
            //把ARGB转换成HSV
            Color.colorToHSV(startValue, startHsv)
            Color.colorToHSV(endValue, endHsv)

            //计算当前动画完成度(fraction)对应的颜色值
            if(endHsv[0] - startHsv[0] > 180) {
                endHsv[0] = endHsv[0] - 360
            } else if(endHsv[0] - startHsv[0] < -180) {
                endHsv[0] = endHsv[0] + 360
            }
            outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction
            if(outHsv[0] > 360) {
                outHsv[0] = outHsv[0] - 360
            } else if(outHsv[0] < 0) {
                outHsv[0] = outHsv[0] + 360
            }
            outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction
            outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction

            //计算当前动画完成度(fraction)所对应的透明度
            val alpha = startValue shr 24 + ((endValue shr 24 - startValue shr 24) * fraction).toInt()

            //把HSV 转换回argb返回
            return Color.HSVToColor(alpha, outHsv)
        }

    }
}