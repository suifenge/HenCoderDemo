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
class Translation: RelativeLayout {

    private lateinit var animateBtn: Button
    private lateinit var imageView: ImageView

    private val translateStateCount = if(SDK_INT > Build.VERSION_CODES.LOLLIPOP) 6 else 4
    private var translationState = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        animateBtn = findViewById(R.id.animateBtn)
        imageView = findViewById(R.id.imageView)

        if(SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            //给音乐图标加上合适的阴影
            imageView.outlineProvider = MusicOutlineProvider()
        }

        animateBtn.setOnClickListener {
            when(translationState) {
                0 -> imageView.animate().translationX(Utils.dpToPixel(200f))
                1 -> imageView.animate().translationX(Utils.dpToPixel(0f))
                2 -> imageView.animate().translationY(Utils.dpToPixel(400f))
                3 -> imageView.animate().translationY(Utils.dpToPixel(0f))
                4 -> {
                    if(SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imageView.animate().translationZ(Utils.dpToPixel(15f))
                    }
                }
                5 -> {
                    if(SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imageView.animate().translationZ(Utils.dpToPixel(0f))
                    }
                }
            }
            translationState ++
            if(translationState == translateStateCount) {
                translationState = 0
            }
        }
    }

    /**
     * 为音乐图标设置三角形的Outline
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    class MusicOutlineProvider: ViewOutlineProvider() {

        private val path = Path()

        init {
            path.moveTo(0f, Utils.dpToPixel(2f))
            path.lineTo(Utils.dpToPixel(7f), Utils.dpToPixel(2f))
            path.lineTo(Utils.dpToPixel(116f), Utils.dpToPixel(58f))
            path.lineTo(Utils.dpToPixel(116f), Utils.dpToPixel(70f))
            path.lineTo(Utils.dpToPixel(7f), Utils.dpToPixel(128f))
            path.lineTo(Utils.dpToPixel(0f), Utils.dpToPixel(120f))
            path.close()
        }

        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setConvexPath(path)
        }
    }
}