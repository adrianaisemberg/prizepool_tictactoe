package com.adrianaisemberg.tictactoe.echo

import android.content.Context
import android.util.AttributeSet
import com.adrianaisemberg.tictactoe.R
import com.adrianaisemberg.tictactoe.common.ViewModelView
import com.adrianaisemberg.tictactoe.databinding.ViewEchoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EchoAuthView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewModelView<EchoAuthViewModel, ViewEchoBinding>(
    layoutId = R.layout.view_echo,
    context = context,
    attrs = attrs,
    defStyleAttr = defStyleAttr,
)