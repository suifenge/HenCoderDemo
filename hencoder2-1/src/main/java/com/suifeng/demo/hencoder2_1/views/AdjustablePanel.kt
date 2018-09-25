package com.suifeng.demo.hencoder2_1.views

import android.content.Context
import android.support.v7.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.SeekBar
import com.suifeng.demo.hencoder2_1.R
import com.suifeng.demo.hencoder2_1.Utils

/**
 * @author ljc
 * @data 2018/9/25
 * @describe
 */
class AdjustablePanel: RelativeLayout {

    lateinit var parentLayout: FrameLayout
    lateinit var heightBar: AppCompatSeekBar
    lateinit var widthBar: AppCompatSeekBar

    private var bottomMargin = Utils.dpToPixel(48f)
    private var minWidth = Utils.dpToPixel(80f)
    private var minHeight = Utils.dpToPixel(100f)

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        parentLayout = findViewById(R.id.parentLayout)
        widthBar = findViewById(R.id.widthBar)
        heightBar = findViewById(R.id.heightBar)
        val listener = object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val layoutParams = parentLayout.layoutParams as LayoutParams
                layoutParams.width = (minWidth + (width - minWidth) * widthBar.progress / 100).toInt()
                layoutParams.height = (minHeight + (height - bottomMargin - minHeight) * heightBar.progress / 100).toInt()
                parentLayout.layoutParams = layoutParams
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        }
        widthBar.setOnSeekBarChangeListener(listener)
        heightBar.setOnSeekBarChangeListener(listener)
    }
}