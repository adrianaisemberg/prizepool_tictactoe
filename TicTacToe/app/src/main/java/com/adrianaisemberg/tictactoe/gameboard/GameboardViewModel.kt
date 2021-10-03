package com.adrianaisemberg.tictactoe.gameboard

import com.adrianaisemberg.tictactoe.GameUpdateListener
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.service.TicTacToeService

class GameboardViewModel(
    val service: TicTacToeService,
) : ViewViewModel {

    val gridSize = 3

    lateinit var gameUpdateListener: GameUpdateListener

    fun onGameUpdated(game: Game) {
        gameUpdateListener.onGameUpdated(game)
    }
}
