package com.adrianaisemberg.tictactoe.gameboard

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.adrianaisemberg.tictactoe.R
import com.adrianaisemberg.tictactoe.databinding.ViewGameboardCellBinding
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.utils.activity
import com.adrianaisemberg.tictactoe.utils.layoutInflater
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameboardCellView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewGameboardCellBinding

    fun setViewModel(game: Game, viewModel: GameboardCellViewModel) {
        initDataBinding()
        binding.viewModel = viewModel
        setOnClickListener { viewModel.onCellClicked(game) }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.inflate<ViewGameboardCellBinding>(
            context.layoutInflater(),
            R.layout.view_gameboard_cell,
            this,
            true
        ).apply {
            lifecycleOwner = activity() as LifecycleOwner
        }
    }
}
