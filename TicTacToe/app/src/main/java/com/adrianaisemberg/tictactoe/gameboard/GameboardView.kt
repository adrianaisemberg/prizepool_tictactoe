package com.adrianaisemberg.tictactoe.gameboard

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.adrianaisemberg.tictactoe.R
import com.adrianaisemberg.tictactoe.common.ViewModelView
import com.adrianaisemberg.tictactoe.databinding.ViewGameboardBinding
import com.adrianaisemberg.tictactoe.service.Game
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewModelView<GameboardViewModel, ViewGameboardBinding>(
    layoutId = R.layout.view_gameboard,
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
) {
    fun setGame(game: Game?) {
        viewModel.game = game
        createBoard()
    }

    private fun createBoard() {
        val game = viewModel.game ?: return
        val rows = game.gameboard.groupBy { cell -> cell.y }

        val layout = LinearLayout(context).apply { orientation = LinearLayout.VERTICAL }
        binding.boardContainer.addView(layout)

        rows.keys.forEach { y ->
            val cells = rows[y] ?: return
            val horizontalLayout =
                LinearLayout(context).apply { orientation = LinearLayout.HORIZONTAL }
            layout.addView(horizontalLayout)

            cells.forEach { cell ->
                horizontalLayout.addView(GameboardCellView(context))
            }
        }
    }
}