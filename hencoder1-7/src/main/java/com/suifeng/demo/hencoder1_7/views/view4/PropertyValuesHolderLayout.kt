package com.suifeng.demo.hencoder1_7.views.view4

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
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
class PropertyValuesHolderLayout: RelativeLayout {

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
            val holder1 = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f)
            val holder2 = PropertyValuesHolder.ofFloat("scaleY", 0f, 1f)
            val holder3 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f)

            ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2, holder3).start()
        }
    }

}