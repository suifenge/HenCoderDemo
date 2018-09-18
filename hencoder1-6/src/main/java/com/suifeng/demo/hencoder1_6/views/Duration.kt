package com.suifeng.demo.hencoder1_6.views

import android.content.Context
import android.graphics.Outline
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import com.suifeng.demo.hencoder1_6.R
import android.os.Build.VERSION.SDK_INT
import android.support.annotation.RequiresApi
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.*
import com.suifeng.demo.hencoder1_6.Utils

/**
 * @author ljc
 * @data 2018/9/18
 * @describe
 */
class Duration: LinearLayout {

    private lateinit var durationSb: SeekBar
    private lateinit var durationValueTv: TextView
    private lateinit var animateBtn: Button
    private lateinit var imageView: ImageView

    private var duration = 300
    private var translationState = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        durationSb = findViewById(R.id.durationSb)
        durationValueTv = findViewById(R.id.durationValueTv)
        durationSb.max = 10
        durationSb.progress = 1
        durationSb.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                duration = progress * 300
                durationValueTv.text = context.getString(R.string.ms_with_value, duration)
            }

        })

        animateBtn = findViewById(R.id.animateBtn)
        imageView = findViewById(R.id.imageView)
        animateBtn.setOnClickListener {
            when(translationState) {
                0 -> imageView.animate().translationX(Utils.dpToPixel(100f)).duration = duration.toLong()
                1 -> imageView.animate().translationX(0f).duration = duration.toLong()
            }
            if(translationState < 1) {
                translationState ++
            } else {
                translationState = 0
            }
        }
    }
}