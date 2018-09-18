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
import android.view.View
import android.view.ViewOutlineProvider
import com.suifeng.demo.hencoder1_6.Utils

/**
 * @author ljc
 * @data 2018/9/18
 * @describe
 */
class Alpha: RelativeLayout {

    private lateinit var animateBtn: Button
    private lateinit var imageView: ImageView

   private var state = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        animateBtn = findViewById(R.id.animateBtn)
        imageView = findViewById(R.id.imageView)

        animateBtn.setOnClickListener {
            when(state) {
                0 -> imageView.animate().alpha(0f)
                1 -> imageView.animate().alpha(1f)
            }
            state ++
            if(state == 2) {
                state = 0
            }
        }
    }
}