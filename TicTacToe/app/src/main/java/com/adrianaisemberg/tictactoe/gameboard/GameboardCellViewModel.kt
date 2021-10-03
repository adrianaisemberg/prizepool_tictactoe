package com.adrianaisemberg.tictactoe.gameboard

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.mvvm.ViewViewModel
import com.adrianaisemberg.tictactoe.service.*
import com.adrianaisemberg.tictactoe.utils.ActionOf
import com.adrianaisemberg.tictactoe.utils.async_io

class GameboardCellViewModel(
    private val service: TicTacToeService,
    private val cell: GameboardCell,
    private val onGameUpdated: ActionOf<Game>,
) : ViewViewModel {

    val image = MutableLiveData(cell.tile.drawableResId)

    fun onCellClicked(game: Game) {
        if (cell.tile != Tile.Empty) return
        if (game.winner != Winner.None) return

        val xcount = game.gameboard.count { it.tile == Tile.X }
        val ocount = game.gameboard.count { it.tile == Tile.O }

        val tile = if (xcount <= ocount) Tile.X else Tile.O

        async_io {
            service.putGameMove(
                gameId = game.gameId,
                x = cell.x,
                y = cell.y,
                tile = tile,
            ).enqueue { response ->
                val updatedGame = response.body() ?: return@enqueue
                refreshCell(updatedGame)
            }
        }
    }

    private fun refreshCell(game: Game) {
        val updatedCell =
            game.gameboard.firstOrNull { it.x == cell.x && it.y == cell.y } ?: return

        cell.tile = updatedCell.tile
        image.value = updatedCell.tile.drawableResId

        onGameUpdated.invoke(game)
    }
}
