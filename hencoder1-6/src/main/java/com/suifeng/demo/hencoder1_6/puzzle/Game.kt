package com.suifeng.demo.hencoder1_6.puzzle

/**
 * @author ljc
 * @data 2018/9/19
 * @describe
 */
interface Game {
    /**
     * 增加难度
     */
    fun addLevel()

    /**
     * 减少难度
     */
    fun reduceLevel()

    /**
     * 修改游戏模式
     */
    fun changeGameMode(gameMode: Int)

    /**
     * 修改游戏图片
     */
    fun changeImage(res: Int)
}