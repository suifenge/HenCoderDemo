package com.suifeng.demo.hencoder1_7.views.view1

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
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
class ArgbEvaluatorLayout: RelativeLayout {

    lateinit var view: ArgbEvaluatorView
    lateinit var animateBtn: Button

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        view = findViewById(R.id.animator_view)
        animateBtn = findViewById(R.id.animate_btn)

        val animator = ObjectAnimator.ofInt(view, "color", Color.parseColor("#ffff0000"), Color.parseColor("#ff00ff00"))
        animator.setEvaluator(ArgbEvaluator())
        animator.interpolator = LinearInterpolator()
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE

        animateBtn.setOnClickListener {
            if(animator.isStarted) return@setOnClickListener
            animator.start()
        }
    }

}