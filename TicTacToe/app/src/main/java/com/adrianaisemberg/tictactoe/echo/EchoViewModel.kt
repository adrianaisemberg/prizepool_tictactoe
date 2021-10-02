package com.adrianaisemberg.tictactoe.echo

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.api.TicTacToeService
import com.adrianaisemberg.tictactoe.api.enqueue
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.utils.async_io

class EchoViewModel(
    private val service: TicTacToeService,
) : ViewViewModel {

    val echoResponse = MutableLiveData<String>()

    override fun onFinishInflate() {
        super.onFinishInflate()

        beginEchoCheck()
    }

    private fun beginEchoCheck() {
        async_io {
            service.getEcho().enqueue(
                onResponse = { response ->
                    echoResponse.value = response.body()
                },
                onFailure = { t ->
                    echoResponse.value = t.message
                }
            )
        }
    }
}
