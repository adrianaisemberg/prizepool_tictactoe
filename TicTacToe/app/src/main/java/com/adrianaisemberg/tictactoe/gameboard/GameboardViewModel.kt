package com.adrianaisemberg.tictactoe.gameboard

import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.*
import com.adrianaisemberg.tictactoe.utils.Drawables
import com.adrianaisemberg.tictactoe.utils.async_io

class GameboardViewModel(
    val service: TicTacToeService,
) : ViewViewModel {

    var game: Game? = null

    val background = Drawables.boardLines()

}
