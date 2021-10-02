package com.adrianaisemberg.tictactoe.gameboard

import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.service.TicTacToeService

class GameboardViewModel(
    private val service: TicTacToeService,
) : ViewViewModel {

    var game: Game? = null
}
