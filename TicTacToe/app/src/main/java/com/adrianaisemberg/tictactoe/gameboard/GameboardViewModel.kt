package com.adrianaisemberg.tictactoe.gameboard

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.GameUpdateListener
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.service.TicTacToeService

class GameboardViewModel(
    val service: TicTacToeService,
) : ViewViewModel {

    val gridSize = 3
    val game = MutableLiveData<Game>()

    lateinit var gameUpdateListener: GameUpdateListener

    fun onGameUpdated(game: Game) {
        this.game.value = game
        gameUpdateListener.onGameUpdated(game)
    }
}
