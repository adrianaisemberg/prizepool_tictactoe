package com.adrianaisemberg.tictactoe.echo

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.utils.async_io
import com.adrianaisemberg.tictactoe.utils.async_ui
import com.adrianaisemberg.tictactoe.utils.scheduleNowAtFixedRate
import java.util.*

open class EchoViewModel(
    private val service: TicTacToeService,
) : ViewViewModel {

    val statusColor = MutableLiveData(COLOR_INITIAL)
    val isLoading = MutableLiveData(false)
    private val timer = Timer()

    override fun onFinishInflate() {
        super.onFinishInflate()

        loopEchoCheck()
    }

    private fun loopEchoCheck() {
        timer.scheduleNowAtFixedRate(INTERVAL) {
            beginEchoCheck()
        }
    }

    protected open fun beginEchoCheck() {
        setLoading()
        async_io {
            service.getEcho().enqueue(
                onResponse = {
                    setSuccess()
                },
                onFailure = {
                    setFailure()
                }
            )
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        timer.cancel()
    }

    protected fun setSuccess() = setStatusColor(COLOR_SUCCESS)
    protected fun setFailure() = setStatusColor(COLOR_FAILURE)
    protected fun setLoading() {
        async_ui {
            isLoading.value = true
        }
    }

    private fun setStatusColor(color: Int) {
        async_ui {
            isLoading.value = false
            statusColor.value = color
        }
    }

    companion object {
        private const val COLOR_INITIAL = 0xff666666.toInt()
        private const val COLOR_SUCCESS = 0xff00ff00.toInt()
        private const val COLOR_FAILURE = 0xffff0000.toInt()
        private const val INTERVAL = 1000L
    }
}
