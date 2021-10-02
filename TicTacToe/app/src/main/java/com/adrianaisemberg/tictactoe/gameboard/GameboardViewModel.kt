package com.adrianaisemberg.tictactoe.gameboard

import com.adrianaisemberg.tictactoe.GameUpdateListener
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.utils.Drawables

class GameboardViewModel(
    val service: TicTacToeService,
) : ViewViewModel {

    var game: Game? = null

    val background = Drawables.boardLines()

    lateinit var gameUpdateListener: GameUpdateListener

    fun onGameUpdated() {
        val game = this.game ?: return
        gameUpdateListener.onGameUpdated(game)
    }
}
