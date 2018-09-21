package com.suifeng.demo.hencoder1_7.views.view6

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.widget.Button
import android.widget.RelativeLayout
import com.suifeng.demo.hencoder1_7.R

/**
 * @author ljc
 * @data 2018/9/21
 * @describe
 */
class KeyFrameLayout: RelativeLayout {

    lateinit var view: KeyFrameView
    lateinit var animateBtn: Button

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        view = findViewById(R.id.animator_view)
        animateBtn = findViewById(R.id.animate_btn)

        animateBtn.setOnClickListener {
            val keyframe1 = Keyframe.ofFloat(0f, 0f)  //开始: progress 为0
            val keyframe2 = Keyframe.ofFloat(0.5f, 100f) //进行到一半是, progress为100
            val keyframe3 = Keyframe.ofFloat(1f, 80f) //结束时倒回到80

            val holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3)
            val animator = ObjectAnimator.ofPropertyValuesHolder(view, holder)
            animator.duration = 2000
            animator.interpolator = FastOutSlowInInterpolator()
            animator.repeatCount = ObjectAnimator.INFINITE
            animator.repeatMode = ObjectAnimator.REVERSE
            animator.start()
        }
    }

}