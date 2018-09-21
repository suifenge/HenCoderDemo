package com.suifeng.demo.hencoder1_7.views.view3

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.PointF
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
class OfObjectLayout: RelativeLayout {

    lateinit var view: OfObjectView
    lateinit var animateBtn: Button

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        view = findViewById(R.id.animator_view)
        animateBtn = findViewById(R.id.animate_btn)

        val animator = ObjectAnimator.ofObject(view, "position", PointFEvaluator(), PointF(0f, 0f), PointF(1f, 1f))
        animator.interpolator = LinearInterpolator()
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE

        animateBtn.setOnClickListener {
            if(animator.isStarted) return@setOnClickListener
            animator.start()
        }
    }

    private inner class PointFEvaluator: TypeEvaluator<PointF> {

        private val newPoint = PointF()

        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val x = startValue.x + (fraction * (endValue.x - startValue.x))
            val y = startValue.y + (fraction * (endValue.y - startValue.y))

            newPoint.set(x, y)
            return newPoint
        }

    }

}