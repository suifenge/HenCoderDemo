package com.suifeng.demo.hencoder1_6.views

import android.content.Context
import android.graphics.Outline
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import com.suifeng.demo.hencoder1_6.R
import android.os.Build.VERSION.SDK_INT
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.ViewOutlineProvider
import com.suifeng.demo.hencoder1_6.Utils

/**
 * @author ljc
 * @data 2018/9/18
 * @describe
 */
class MultiProperties: ConstraintLayout {

    private lateinit var animateBtn: Button
    private lateinit var imageView: ImageView

    private var animated: Boolean = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        animateBtn = findViewById(R.id.animateBtn)
        imageView = findViewById(R.id.imageView)

        imageView.scaleX = 0f
        imageView.scaleY = 0f
        imageView.alpha = 0f

        animateBtn.setOnClickListener {
            if(!animated) {
                imageView.animate()
                        .translationX(Utils.dpToPixel(200f))
                        .rotation(360f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .alpha(1f)
            } else {
                imageView.animate()
                        .translationX(0f)
                        .rotation(0f)
                        .scaleX(0f)
                        .scaleY(0f)
                        .alpha(0f)
            }
            animated = !animated
        }
    }
}