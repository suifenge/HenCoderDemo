package com.suifeng.demo.hencoder1_6.puzzle

import android.graphics.Bitmap
import android.widget.ImageView

/**
 * @author ljc
 * @data 2018/9/19
 * @describe
 */
class ImagePiece {

    companion object {
        const val TYPE_NORMAL = 0
        const val TYPE_EMPTY = 1
    }

    var type = TYPE_NORMAL
    var index = -1
    var bitmap: Bitmap? = null
    var imageView: ImageView? = null

}