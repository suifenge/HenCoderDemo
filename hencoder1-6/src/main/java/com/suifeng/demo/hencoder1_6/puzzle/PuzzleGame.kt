package com.suifeng.demo.hencoder1_6.puzzle

import android.content.Context
import android.support.annotation.NonNull

/**
 * @author ljc
 * @data 2018/9/19
 * @describe
 */
class PuzzleGame(@NonNull context: Context, @NonNull private var puzzleLayout: PuzzleLayout): Game, PuzzleLayout.SuccessListener {

    private var stateListener: GameStateListener? = null
    private var context: Context = context.applicationContext

    fun setGameStateListener(stateListener: GameStateListener) {
        this.stateListener = stateListener
    }

    init {
        puzzleLayout.setSuccessListener(this)
    }

    override fun addLevel() {
        if(!puzzleLayout.addColumn()) {
            //最高等级
        }
        stateListener?.setLevel(getLevel())
    }

    override fun reduceLevel() {
        if(!puzzleLayout.reduceColumn()) {
            //最低等级
        }
        stateListener?.setLevel(getLevel())
    }

    override fun changeGameMode(gameMode: Int) {
        puzzleLayout.changeGameMode(gameMode)
    }

    override fun changeImage(res: Int) {
        puzzleLayout.changePicture(res)
    }

    fun getLevel(): Int {
        val column = puzzleLayout.getColumn()
        return column - 3 + 1
    }

    override fun success() {
        stateListener?.gameSuccess(getLevel())
    }

    interface GameStateListener {
        fun setLevel(level: Int)

        fun gameSuccess(level: Int)
    }

}