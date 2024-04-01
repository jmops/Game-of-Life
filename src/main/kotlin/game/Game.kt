package org.example.game

import org.example.canvas.*

class Game(gameInitialState: GameInitialState) {

    private val cellSize = 3
    private val gameTickMs = 100L
    private val numberOfNodeRows = 400
    private val numberOfNodeColumns = 700

    private val board: Array<Array<Node>> =
        InitialGameBoardFactory(gameInitialState, numberOfNodeRows, numberOfNodeColumns, cellSize).getBoard()
    private val container =
        ContainerFrame(GamePanel(board, cellSize), cellSize * numberOfNodeColumns, cellSize * numberOfNodeRows)

    fun startGame() {
        while (true) { // TODO next round async while sleeping
            playRound()
            Thread.sleep(gameTickMs)
            container.repaint()
        }
    }

    private fun playRound() {
        for (iY in board.indices) {
            for (iX in board[iY].indices) {
                updateNode(iY, iX)
            }
        }
        updateLife()
    }

    private fun updateLife() {
        for (nodes in board) {
            for (node in nodes) {
                if (node.changeLifeState) {
                    node.alive = !node.alive
                    node.changeLifeState = false
                }
            }
        }
    }

    private fun updateNode(iy: Int, ix: Int) {
        var neighboursAlive = 0
        val node = board[iy][ix]

        if (iy > 0 && board[iy - 1][ix].alive) {
            neighboursAlive++
        }
        if (iy > 0 && ix > 0 && board[iy - 1][ix - 1].alive) {
            neighboursAlive++
        }
        if (iy > 0 && ix < board[iy].size - 1 && board[iy - 1][ix + 1].alive) {
            neighboursAlive++
        }
        if (ix > 0 && board[iy][ix - 1].alive) {
            neighboursAlive++
        }
        if (ix < board[iy].size - 1 && board[iy][ix + 1].alive) {
            neighboursAlive++
        }
        if (iy < board.size - 1 && board[iy + 1][ix].alive) {
            neighboursAlive++
        }
        if (iy < board.size - 1 && ix > 0 && board[iy + 1][ix - 1].alive) {
            neighboursAlive++
        }
        if (iy < board.size - 1 && ix < board[iy].size - 1 && board[iy + 1][ix + 1].alive) {
            neighboursAlive++
        }
        val nodeDies = node.alive && (neighboursAlive < 2 || neighboursAlive > 3)
        val nodeBirthed = !node.alive && neighboursAlive == 3
        if (nodeDies || nodeBirthed) {
            node.changeLifeState = true
        }
    }
}