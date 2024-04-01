package org.example.game

import kotlin.random.Random

class InitialGameBoardFactory(
    private val gameInitialState: GameInitialState,
    private val height: Int,
    private val width: Int,
    private val cellSize: Int
) {

    fun getBoard(): Array<Array<Node>> {
        val board: Array<Array<Node>> = Array(height) {
            Array(width) {
                Node(-1, -1, false)
            }
        }
        for (iy in board.indices) {
            for (ix in board[iy].indices) {
                board[iy][ix] = Node(ix * cellSize, iy * cellSize, false)
            }
        }
        when (gameInitialState) {
            GameInitialState.GLIDER -> initGlider(board)
            GameInitialState.BLINKER -> initBlinker(board)
            GameInitialState.RANDOM_COMMON -> initRandom(board, 4)
            GameInitialState.RANDOM_RARE -> initRandom(board, 12)
            GameInitialState.RANDOM_TOP_LEFT -> initRandomTopLeft(board)
        }

        return board
    }

    private fun initRandomTopLeft(board: Array<Array<Node>>) {
        for (iy in board.indices) {
            for (ix in board[iy].indices) {
                if (iy < board.size/2 && ix < board[iy].size/ 2 && shouldStartAlive(2)) {
                    board[iy][ix] = Node(ix * cellSize, iy * cellSize, true)
                } else {
                    board[iy][ix] = Node(ix * cellSize, iy * cellSize, false)
                }
            }
        }
    }

    private fun initBlinker(board: Array<Array<Node>>) {
        val iy = board.size / 2
        board[iy][board[iy].size / 2].alive = true
        board[iy][board[iy].size / 2 + 1].alive = true
        board[iy][board[iy].size / 2 + 2].alive = true
    }

    private fun initGlider(board: Array<Array<Node>>) {
        val iy = board.size / 2
        board[iy][board[iy].size / 2].alive = true
        board[iy][board[iy].size / 2 + 1].alive = true
        board[iy][board[iy].size / 2 + 2].alive = true
        board[iy + 1][board[iy].size / 2].alive = true
        board[iy + 2][board[iy].size / 2 + 1].alive = true
    }

    private fun initRandom(board: Array<Array<Node>>, moduloRandomBy: Int) {
        for (iy in board.indices) {
            for (ix in board[iy].indices) {
                if (shouldStartAlive(moduloRandomBy)) {
                    board[iy][ix] = Node(ix * cellSize, iy * cellSize, true)
                } else {
                    board[iy][ix] = Node(ix * cellSize, iy * cellSize, false)
                }
            }
        }
    }

    private fun shouldStartAlive(modulo: Int): Boolean {
        return Random.nextInt() % modulo == 0
    }
}