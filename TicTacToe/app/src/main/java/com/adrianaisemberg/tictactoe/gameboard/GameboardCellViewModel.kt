package com.adrianaisemberg.tictactoe.gameboard

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.*
import com.adrianaisemberg.tictactoe.utils.Action
import com.adrianaisemberg.tictactoe.utils.async_io

class GameboardCellViewModel(
    private val service: TicTacToeService,
    private val game: Game,
    private val cell: GameboardCell,
    private val onGameUpdated: Action,
) : ViewViewModel {

    val image = MutableLiveData(cell.tile.drawableResId)

    fun onCellClicked() {
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
            ).enqueue(
                onResponse = { response ->
                    val updatedGame = response.body() ?: return@enqueue
                    refreshCell(updatedGame)
                },
                onFailure = { t ->

                }
            )
        }
    }

    private fun refreshCell(game: Game) {
        val updatedCell =
            game.gameboard.firstOrNull { it.x == cell.x && it.y == cell.y } ?: return

        this.game.winner = game.winner
        this.game.status = game.status
        cell.tile = updatedCell.tile
        image.value = updatedCell.tile.drawableResId

        onGameUpdated.invoke()
    }
}
