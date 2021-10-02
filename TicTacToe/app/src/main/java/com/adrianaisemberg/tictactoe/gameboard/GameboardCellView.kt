package com.adrianaisemberg.tictactoe.gameboard

import android.content.Context
import android.util.AttributeSet
import com.adrianaisemberg.tictactoe.R
import com.adrianaisemberg.tictactoe.common.ViewModelView
import com.adrianaisemberg.tictactoe.databinding.ViewGameboardCellBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameboardCellView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewModelView<GameboardCellViewModel, ViewGameboardCellBinding>(
    layoutId = R.layout.view_gameboard_cell,
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
)
