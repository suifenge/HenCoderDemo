package com.suifeng.demo.hencoder1_6.puzzle

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import com.suifeng.demo.hencoder1_6.R
import com.suifeng.demo.hencoder1_6.Utils
import java.util.*
import kotlin.Comparator


/**
 * @author ljc
 * @data 2018/9/19
 * @describe
 */
class PuzzleLayout: FrameLayout, View.OnClickListener {

    companion object {
        const val GAME_MODE_NORMAL = 0
        const val GAME_MODE_EXCHANGE = 1
    }

    private val DEFAULT_MARGIN = 3

    //游戏模式
    private var mGameMode = GAME_MODE_EXCHANGE

    //拼图布局为正方形，宽度为屏幕的宽度
    private var mViewWidth = 0

    //拼图游戏每一行的图片的图片个数，默认是三个
    private var mColumn = 3

    //每张图片的宽度
    private var mItemWidth = 0

    //拼图游戏bitmap集合
    private lateinit var mImagePieces: ArrayList<ImagePiece>

    //每个图片设置大小
    lateinit var layoutParams: FrameLayout.LayoutParams

    //大图
    private var mBitmap: Bitmap? = null

    //动画层
    private var mAnimLayout: RelativeLayout? = null

    //小图之间的margin
    private var mMargin = 0

    //View的padding
    private var mPadding = 0

    //选中的第一张图片
    private var mFirst: ImageView? = null

    //选中第二张图片
    private var mSecond: ImageView? = null

    //是否添加了动画层
    private var isAddAnimatorLayout = false

    //是否正在进行动画
    private var isAnimation = false

    private var mRes = R.drawable.ll

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(context)
        initBitmaps()
        initBitmapWidth()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(mViewWidth, mViewWidth)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (i in 0 until childCount) {
            if(getChildAt(i) is ImageView) {
                val imageView = getChildAt(i) as ImageView
                imageView.layout(imageView.left, imageView.top, imageView.right, imageView.bottom)
            } else {
                val relativeLayout = getChildAt(i) as RelativeLayout
                relativeLayout.layout(0, 0, mViewWidth, mViewWidth)
            }
        }
    }

    /**
     * 初始化初始变量
     */
    private fun init(context: Context) {
        mMargin = Utils.dp2px(context, DEFAULT_MARGIN)
        mViewWidth = Utils.getScreenWidth(context)[0]
        mPadding = Utils.getMinLength(paddingBottom, paddingLeft, paddingRight, paddingTop)
        mItemWidth = (mViewWidth - mPadding * 2 - mMargin * (mColumn - 1)) / mColumn
    }

    /**
     * 将大图切割成多个小图
     */
    private fun initBitmaps() {
        if(mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(resources, mRes)
        }
        mImagePieces = Utils.splitImage(context, mBitmap, mColumn, mGameMode)
        sortImagePieces()
    }

    /**
     * 对ImagePieces进行排序
     */
    private fun sortImagePieces() {
        try {
            mImagePieces.sortWith(Comparator { o1, o2 ->
                if(Math.random() > 0.5) 1 else -1
            })
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if(mGameMode == GAME_MODE_NORMAL) {
                //如果是普通模式就把空图放在最后
                var tempImagePiece: ImagePiece? = null
                var tempIndex = 0
                for (i in 0 until mImagePieces.size) {
                    val imagePiece = mImagePieces[i]
                    if(imagePiece.type == ImagePiece.TYPE_EMPTY) {
                        tempImagePiece = imagePiece
                        tempIndex = i
                        break
                    }
                }
                if(tempImagePiece == null) return
                mImagePieces.removeAt(tempIndex)
                mImagePieces.add(mImagePieces.size, tempImagePiece)
            }
        }
    }

    /**
     * 设置图片的大小和layout的属性
     */
    private fun initBitmapWidth() {
        var line = 0
        var left: Int
        var top: Int
        var right: Int
        var bottom: Int
        mImagePieces.forEachIndexed { index, imagePiece ->
            val imageView = ImageView(context)
            imageView.setImageBitmap(imagePiece.bitmap)
            layoutParams = LayoutParams(mItemWidth, mItemWidth)
            imageView.layoutParams = layoutParams
            if(index != 0 && index % mColumn == 0) {
                line ++
            }
            if(index % mColumn == 0) {
                left = index % mColumn * mItemWidth
            } else {
                left = index % mColumn * mItemWidth + (index % mColumn) * mMargin
            }
            top = mItemWidth * line + line * mMargin
            right = left + mItemWidth
            bottom = top + mItemWidth
            imageView.right = right
            imageView.left = left
            imageView.top = top
            imageView.bottom = bottom
            imageView.id = index
            imageView.setOnClickListener(this)
            imagePiece.imageView = imageView
            addView(imageView)
        }
    }

    /**
     * 更改游戏模式
     * 1.点击交换模式，默认
     * 2.滑块滑动模式
     */
    fun changeGameMode(gameMode: Int) {
        if(mGameMode == gameMode) {
            return
        }
        this.mGameMode = gameMode
        reset()
    }

    /**
     * 重置View
     */
    fun reset() {
        mItemWidth = (mViewWidth - mPadding * 2 - mMargin * (mColumn -1)) / mColumn
        if(mImagePieces != null) {
            mImagePieces.clear()
        }
        isAddAnimatorLayout = false
        mBitmap = null
        removeAllViews()
        initBitmaps()
        initBitmapWidth()
    }

    /**
     * 添加列，最多7列
     */
    fun addColumn(): Boolean {
        mColumn ++
        if(mColumn > 7) {
            mColumn --
            return false
        }
        reset()
        return true
    }

    /**
     * 更改图片资源
     */
    fun changePicture(res: Int) {
        this.mRes = res
        reset()
    }

    /**
     * 减少column，最少3列
     */
    fun reduceColumn(): Boolean {
        mColumn --
        if(mColumn < 3) {
            mColumn ++
            return false
        }
        reset()
        return true
    }

    override fun onClick(v: View?) {
        if(isAnimation) {
            //还在运行动画的时候，不允许点击
            return
        }
        if(v !is ImageView) {
            return
        }
        if(GAME_MODE_NORMAL == mGameMode) {
            val imageView = v as ImageView
            val imagePiece = mImagePieces[imageView.id]
            if(imagePiece.type == ImagePiece.TYPE_EMPTY) {
                //普通模式点击空图时，不做处理
                return
            }
            if(mFirst == null) {
                mFirst = v
            }
            checkEmptyImage(mFirst!!)
        } else {
            //点的是同一个View
            if (mFirst === v) {
                mFirst!!.colorFilter = null
                mFirst = null
                return
            }
            if (mFirst == null) {
                mFirst = v
                //选中之后添加一层颜色
                mFirst!!.setColorFilter(Color.parseColor("#55FF0000"))
            } else {
                mSecond = v
                exChangeView()
            }
        }
    }

    private fun checkEmptyImage(imageView: ImageView) {
        val index = imageView.id
        val line = mImagePieces.size / mColumn
        var imagePiece: ImagePiece? = null
        if(index < mColumn) {
            //第一行（需额外计算，上一行和下一行是否有空图）
            imagePiece = checkCurrentLine(index)
            //判断下一行同一列的图片是否为空
            imagePiece = checkOtherLine(index + mColumn, imagePiece)
        } else if(index < (line - 1) * mColumn) {
            //中间的行（需要额外计算，上一行和下一行是否有空图）
            imagePiece = checkCurrentLine(index)
            //判断上一行同一列的图片是否为空
            imagePiece = checkOtherLine(index - mColumn, imagePiece)
            //判断下一行同一列的图片是否为空
            imagePiece = checkOtherLine(index + mColumn, imagePiece)
        } else {
            //最后一行（需要额外计算，上一行是否有空图））
            imagePiece = checkCurrentLine(index)
            //检查上一行同一列有没有空图
            imagePiece = checkOtherLine(index - mColumn, imagePiece)
        }
        if(imagePiece == null) {
            //周围没有空的imageView
            mFirst = null
            mSecond = null
        } else {
            //记录下第二张ImageView
            mSecond = imagePiece.imageView
            //选中第二个图片，开启动两张图片替换的动画
            exChangeView()
        }
    }

    private fun checkCurrentLine(index: Int): ImagePiece?{
        var imagePiece: ImagePiece? = null
        //第一行
        if(index % mColumn == 0) {
            //第一个
            imagePiece = getCheckEmptyImageView(index + 1)
        } else if(index % mColumn == mColumn -1) {
            //最后一个
            imagePiece = getCheckEmptyImageView(index - 1)
        } else {
            imagePiece = getCheckEmptyImageView(index + 1)
            if(imagePiece == null) {
                imagePiece = getCheckEmptyImageView(index - 1)
            }
        }
        return imagePiece
    }

    private fun getCheckEmptyImageView(index: Int): ImagePiece? {
        val imagePiece = mImagePieces[index]
        if(imagePiece.type == ImagePiece.TYPE_EMPTY) {
            //找到空的imageView
            return imagePiece
        }
        return null
    }

    private fun checkOtherLine(index: Int, imagePiece: ImagePiece?): ImagePiece? {
        return if(imagePiece != null) {
            imagePiece
        } else {
            getCheckEmptyImageView(index)
        }
    }

    private fun addAnimationImageView(imageView: ImageView): ImageView {
        val getImage = ImageView(context)
        val firstParams = RelativeLayout.LayoutParams(mItemWidth, mItemWidth)
        firstParams.leftMargin = imageView.left - mPadding
        firstParams.topMargin = imageView.top - mPadding
        val firstBitmap = mImagePieces[imageView.id].bitmap
        getImage.setImageBitmap(firstBitmap)
        getImage.layoutParams = firstParams
        mAnimLayout?.addView(getImage)
        return getImage
    }

    /**
     * 添加动画层，并且添加平移的动画
     */
    private fun exChangeView() {
        //添加动画层
        setUpAnimLayout()
        //添加第一个图片
        val first = addAnimationImageView(mFirst!!)
        //添加另一个图片
        val second = addAnimationImageView(mSecond!!)

        val secondXAnimator = ObjectAnimator.ofFloat(second, "TranslationX", 0f, -(mSecond!!.left - mFirst!!.left).toFloat())
        val secondYAnimator = ObjectAnimator.ofFloat(second, "TranslationY", 0f, -(mSecond!!.top - mFirst!!.top).toFloat())
        val firstXAnimator = ObjectAnimator.ofFloat(first, "TranslationX", 0f, (mSecond!!.left - mFirst!!.left).toFloat())
        val firstYAnimator = ObjectAnimator.ofFloat(first, "TranslationY", 0f, (mSecond!!.top - mFirst!!.top).toFloat())
        val secondAnimator = AnimatorSet()
        secondAnimator.play(secondXAnimator).with(secondYAnimator).with(firstXAnimator).with(firstYAnimator)
        secondAnimator.duration = 300

        val firstPiece = mImagePieces[mFirst!!.id]
        val secondPiece = mImagePieces[mSecond!!.id]
        val firstType = firstPiece.type
        val secondType = secondPiece.type
        val firstBitmap = mImagePieces[mFirst!!.id].bitmap
        val secondBitmap = mImagePieces[mSecond!!.id].bitmap
        //        final int firstIndex = mImagePieces.get(mFirst.getId()).getIndex();
        //        final int secondIndex = mImagePieces.get(mFirst.getId()).getIndex();
        secondAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                val firstIndex = firstPiece.index
                val secondIndex = secondPiece.index
                if (mFirst != null) {
                    mFirst!!.colorFilter = null
                    mFirst!!.visibility = View.VISIBLE
                    mFirst!!.setImageBitmap(secondBitmap)
                    firstPiece.bitmap = secondBitmap
                    firstPiece.index = secondIndex
                }
                if (mSecond != null) {
                    mSecond!!.visibility = View.VISIBLE
                    mSecond!!.setImageBitmap(firstBitmap)
                    secondPiece.bitmap = firstBitmap
                    secondPiece.index = firstIndex
                }
                if (mGameMode.equals(GAME_MODE_NORMAL)) {
                    firstPiece.type = secondType
                    secondPiece.type = firstType
                }

                mAnimLayout?.removeAllViews()
                mAnimLayout?.visibility = View.GONE
                mFirst = null
                mSecond = null
                isAnimation = false
                invalidate()
                if (checkSuccess()) {
                    mSuccessListener?.success()
                }
            }

            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                isAnimation = true
                mAnimLayout?.visibility = View.VISIBLE
                mFirst!!.visibility = View.INVISIBLE
                mSecond!!.visibility = View.INVISIBLE
            }
        })
        secondAnimator.start()
    }

    /**
     * 构造动画层 用于点击之后的动画
     * 为什么要做动画层？ 要保证动画在整个view上面执行。
     */
    private fun setUpAnimLayout() {
        if (mAnimLayout == null) {
            mAnimLayout = RelativeLayout(context)
        }
        if (!isAddAnimatorLayout) {
            isAddAnimatorLayout = true
            addView(mAnimLayout)
        }
    }

    /**
     * 检测是否成功
     */
    private fun checkSuccess(): Boolean {
        var isSuccess = true
        for (i in 0 until mImagePieces.size) {
            val imagePiece = mImagePieces[i]
            if (i != imagePiece.index) {
                isSuccess = false
            }
        }
        return isSuccess
    }

    fun getBitmap(): Bitmap? {
        return mBitmap
    }

    fun getRes(): Int {
        return mRes
    }

    fun getColumn(): Int {
        return mColumn
    }

    private var mSuccessListener: SuccessListener? = null

    fun setSuccessListener(successListener: SuccessListener) {
        this.mSuccessListener = successListener
    }

    interface SuccessListener {
        fun success()
    }
}