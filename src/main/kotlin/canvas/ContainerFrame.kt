package org.example.canvas

import javax.swing.JFrame

class ContainerFrame(gamePanel: GamePanel, width: Int, height: Int): JFrame() {

    init {
        val topBorder = insets.top
        val bottomBorder = insets.bottom
        val leftBorder = insets.left
        val rightBorder = insets.right
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(width + leftBorder + rightBorder, height + topBorder + bottomBorder)
        isVisible = true
        contentPane.setSize(width, height)
        contentPane.add(gamePanel)
    }
}