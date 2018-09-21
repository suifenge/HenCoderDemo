package com.suifeng.demo.hencoder1_7.views.view5

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import com.suifeng.demo.hencoder1_7.R

/**
 * @author ljc
 * @data 2018/9/21
 * @describe
 */
class AnimatorSetLayout: RelativeLayout {

    lateinit var view: View
    lateinit var animateBtn: Button

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        view = findViewById(R.id.animator_view)
        animateBtn = findViewById(R.id.animate_btn)

        animateBtn.setOnClickListener {
            view.translationX = -200f
            val animator1 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
            val animator2 = ObjectAnimator.ofFloat(view, "translationX", -200f, 200f)
            val animator3 = ObjectAnimator.ofFloat(view, "rotation", 0f, 1080f)
            animator3.duration = 1000

            val animatorSet = AnimatorSet()
            animatorSet.play(animator1).before(animator2)   //先执行1 再执行2
            animatorSet.playTogether(animator2, animator3) // 2 和 3同时开始

            animatorSet.start()
        }
    }

}