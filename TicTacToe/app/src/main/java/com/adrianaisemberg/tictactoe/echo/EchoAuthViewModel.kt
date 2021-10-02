package com.adrianaisemberg.tictactoe.echo

import com.adrianaisemberg.tictactoe.common.Common
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.utils.async_io

class EchoAuthViewModel(
    private val common: Common,
    private val service: TicTacToeService,
) : EchoViewModel(service) {

    override fun beginEchoCheck() {
        super.setLoading()
        common.settings.authenticationKey?.let { key ->
            async_io {
                service.getEchoAuth(key).enqueue(
                    onResponse = {
                        super.setSuccess()
                    },
                    onFailure = { t ->
                        // TODO: handle exception
                        super.setFailure()
                    }
                )
            }
        }
    }
}
