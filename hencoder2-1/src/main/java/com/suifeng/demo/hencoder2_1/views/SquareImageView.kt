package com.suifeng.demo.hencoder2_1.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

/**
 * @author ljc
 * @data 2018/9/25
 * @describe
 */
class SquareImageView: ImageView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var measuredWidth = measuredWidth
        var measuredHeight = measuredHeight
        if(measuredWidth > measuredHeight) {
            measuredWidth = measuredHeight
        } else {
            measuredHeight = measuredWidth
        }
        setMeasuredDimension(measuredWidth, measuredHeight)
    }
}