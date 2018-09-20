package com.suifeng.demo.hencoder1_6.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suifeng.demo.hencoder1_6.R
import com.suifeng.demo.hencoder1_6.puzzle.PuzzleGame
import com.suifeng.demo.hencoder1_6.puzzle.PuzzleLayout


/**
 * @author ljc
 * @data 2018/9/7
 * @describe
 */
class PuzzleFragment: Fragment(), PuzzleGame.GameStateListener {

    lateinit var puzzleLayout: PuzzleLayout
    lateinit var puzzleGame: PuzzleGame

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_8, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        puzzleLayout = view.findViewById(R.id.puzzle_layout)
        puzzleGame = PuzzleGame(activity!!, puzzleLayout)
        puzzleGame.setGameStateListener(this)
    }

    override fun setLevel(level: Int) {
        Log.i("wtf", "当前难度--->$level")
    }

    override fun gameSuccess(level: Int) {
        Log.i("wtf", "游戏完成--->$level")
    }

}