package com.adrianaisemberg.tictactoe

import com.adrianaisemberg.tictactoe.service.Game

interface GameUpdateListener {
    fun onGameUpdated(game: Game)
}