package com.adrianaisemberg.tictactoe.echo

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.utils.async_io

open class EchoViewModel(
    private val service: TicTacToeService,
) : ViewViewModel {

    val statusColor = MutableLiveData(COLOR_LOADING)

    override fun onFinishInflate() {
        super.onFinishInflate()

        beginEchoCheck()
    }

    protected open fun beginEchoCheck() {
        setLoading()
        async_io {
            service.getEcho().enqueue(
                onResponse = {
                    setSuccess()
                },
                onFailure = { t ->
                    // TODO: handle exception
                    setFailure()
                }
            )
        }
    }

    protected fun setSuccess() = setStatusColor(COLOR_SUCCESS)
    protected fun setFailure() = setStatusColor(COLOR_SUCCESS)
    protected fun setLoading() = setStatusColor(COLOR_SUCCESS)

    private fun setStatusColor(color: Int) {
        statusColor.value = color
    }

    companion object {
        private const val COLOR_LOADING = 0xff666666.toInt()
        private const val COLOR_SUCCESS = 0xff00ff00.toInt()
        private const val COLOR_FAIL = 0xffff0000.toInt()
    }
}
