package com.adrianaisemberg.tictactoe.echo

import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.utils.Scheduler
import com.adrianaisemberg.tictactoe.utils.async_io

class EchoAuthViewModel(
    private val service: TicTacToeService,
    private val scheduler: Scheduler,
) : EchoViewModel(service, scheduler) {

    override fun beginEchoCheck() {
        super.setLoading()
        async_io {
            service.getEchoAuth().enqueue(
                onResponse = {
                    super.setSuccess()
                },
                onFailure = {
                    super.setFailure()
                }
            )
        }
    }
}
