package com.adrianaisemberg.tictactoe.gameboard

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.adrianaisemberg.tictactoe.GameUpdateListener
import com.adrianaisemberg.tictactoe.R
import com.adrianaisemberg.tictactoe.common.ViewModelView
import com.adrianaisemberg.tictactoe.databinding.ViewGameboardBinding
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.utils.layoutInflater
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
    fun setOnGameUpdatedListener(listener: GameUpdateListener?) {
        viewModel.gameUpdateListener = listener ?: return
    }

    fun onGameUpdated(game: Game) {
        viewModel.onGameUpdated(game)
    }

    @SuppressLint("InflateParams")
    fun createBoard(game: Game) {
        val rows = game.gameboard.groupBy { cell -> cell.y }
        val layoutInflater = context.layoutInflater()

        binding.boardContainer.removeAllViews()

        rows.values.forEach { cells ->
            val rowLayout =
                layoutInflater.inflate(R.layout.view_gameboard_row, null) as LinearLayout

            binding.boardContainer.addView(rowLayout)

            cells.forEach { cell ->
                rowLayout.addView(GameboardCellView(context).apply {
                    setViewModel(
                        game = game,
                        viewModel = GameboardCellViewModel(
                            service = viewModel.service,
                            cell = cell,
                            onGameUpdated = viewModel::onGameUpdated,
                        )
                    )
                })
            }
        }
    }
}

@BindingAdapter("game", "onGameUpdated")
fun GameboardView.setGameAndListener(game: Game?, onGameUpdated: GameUpdateListener?) {
    if (game != null && onGameUpdated != null) {
        createBoard(game)
        setOnGameUpdatedListener(onGameUpdated)
        onGameUpdated(game)
    }
}