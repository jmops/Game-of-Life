package org.example

import org.example.game.Game
import org.example.game.GameInitialState

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Game(GameInitialState.RANDOM_TOP_LEFT).startGame()
}