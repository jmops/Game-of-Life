package org.example.canvas

import org.example.game.Node
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class GamePanel(private val board: Array<Array<Node>>, private val cellSize: Int): JPanel() {
    override fun paint(graphics: Graphics) {
        val graphics2d = graphics as Graphics2D
        graphics2d.color = Color.BLACK
        graphics2d.fillRect(0, 0, board[0].size * cellSize, board.size * cellSize)
        graphics2d.color = Color.WHITE
        for (nodes in board) {
            for (node in nodes) {
                if (node.alive) {
                    graphics2d.fillRect(node.x, node.y, cellSize, cellSize)
                }
            }
        }
    }



}