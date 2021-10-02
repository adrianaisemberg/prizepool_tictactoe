package com.adrianaisemberg.tictactoe.echo

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.utils.async_io

class EchoViewModel(
    private val service: TicTacToeService,
) : ViewViewModel {

    val statusColor = MutableLiveData(COLOR_LOADING)

    override fun onFinishInflate() {
        super.onFinishInflate()

        beginEchoCheck()
    }

    private fun beginEchoCheck() {
        async_io {
            service.getEcho().enqueue(
                onResponse = {
                    statusColor.value = COLOR_SUCCESS
                },
                onFailure = { t ->
                    statusColor.value = COLOR_FAIL
                }
            )
        }
    }

    companion object {
        private const val COLOR_LOADING = 0xff666666.toInt()
        private const val COLOR_SUCCESS = 0xff00ff00.toInt()
        private const val COLOR_FAIL = 0xffff0000.toInt()
    }
}
