package com.adrianaisemberg.tictactoe.gameboard

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.R
import com.adrianaisemberg.tictactoe.mvvm.ViewViewModel
import com.adrianaisemberg.tictactoe.service.*
import com.adrianaisemberg.tictactoe.utils.ActionOf
import com.adrianaisemberg.tictactoe.utils.async_io

class GameboardCellViewModel(
    private val service: TicTacToeService,
    private val cell: GameboardCell,
    private val onGameUpdated: ActionOf<Game>,
) : ViewViewModel {

    val image = MutableLiveData(mapCellTileToImage(cell.tile))

    fun onCellClicked(game: Game) {
        // if already clicked
        if (cell.tile != Tile.Empty) return

        // if the game is over (hen there is a winner)
        if (game.winner != Winner.None) return

        // check which tile is next based on the number of existing tiles
        // if the counts are same - x moves (x is first)
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
        // find the updated cell based on the index
        val updatedCell =
            game.gameboard.firstOrNull { it.x == cell.x && it.y == cell.y } ?: return

        // update current cell and bound ui
        cell.tile = updatedCell.tile
        image.value = mapCellTileToImage(updatedCell.tile)

        // update overall game listener (in case there's aa winner)
        onGameUpdated.invoke(game)
    }

    private fun mapCellTileToImage(tile: Tile): Int = when (tile) {
        Tile.Empty -> R.drawable.ic_empty
        Tile.X -> R.drawable.ic_x
        Tile.O -> R.drawable.ic_o
    }
}
