package com.suifeng.demo.hencoder1_6.views

import android.content.Context
import android.graphics.Path
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v4.view.animation.PathInterpolatorCompat
import android.util.AttributeSet
import android.view.animation.*
import android.view.animation.Interpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import com.suifeng.demo.hencoder1_6.R
import com.suifeng.demo.hencoder1_6.Utils

/**
 * @author ljc
 * @data 2018/9/18
 * @describe
 */
class Interpolator: LinearLayout {

    private lateinit var spinner: Spinner
    private lateinit var animateBtn: Button
    private lateinit var imageView: ImageView

    private val interpolators = ArrayList<Interpolator>()
    private val interpolatorPath = Path()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        interpolatorPath.lineTo(0.25f, 0.25f)
        interpolatorPath.moveTo(0.25f, 1.5f)
        interpolatorPath.lineTo(1f, 1f)

        interpolators.add(AccelerateDecelerateInterpolator())
        interpolators.add(LinearInterpolator())
        interpolators.add(AccelerateInterpolator())
        interpolators.add(DecelerateInterpolator())
        interpolators.add(AnticipateInterpolator())
        interpolators.add(OvershootInterpolator())
        interpolators.add(AnticipateOvershootInterpolator())
        interpolators.add(BounceInterpolator())
        interpolators.add(CycleInterpolator(0.5f))
        interpolators.add(PathInterpolatorCompat.create(interpolatorPath))
        interpolators.add(FastOutLinearInInterpolator())
        interpolators.add(FastOutSlowInInterpolator())
        interpolators.add(LinearOutSlowInInterpolator())
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        spinner = findViewById(R.id.interpolatorSpinner)

        animateBtn = findViewById(R.id.animateBtn)
        imageView = findViewById(R.id.imageView)
        animateBtn.setOnClickListener {
            imageView.animate()
                    .translationX(Utils.dpToPixel(150f))
                    .setDuration(600)
                    .setInterpolator(interpolators[spinner.selectedItemPosition])
                    .withEndAction {
                        imageView.postDelayed({
                            imageView.translationX = 0f
                        }, 1000)
                    }
        }
    }
}
