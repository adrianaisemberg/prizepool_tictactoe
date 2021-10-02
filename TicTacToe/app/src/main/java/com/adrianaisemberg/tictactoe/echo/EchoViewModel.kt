package com.adrianaisemberg.tictactoe.echo

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.utils.async_io
import com.adrianaisemberg.tictactoe.utils.async_ui
import java.util.*
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

open class EchoViewModel(
    private val service: TicTacToeService,
) : ViewViewModel {

    val statusColor = MutableLiveData(COLOR_LOADING)

    override fun onFinishInflate() {
        super.onFinishInflate()

        loopEchoCheck()
    }

    private fun loopEchoCheck() {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                beginEchoCheck()
            }

        }, 0L, 1000L)
/*
        val executor = ScheduledThreadPoolExecutor(1)
        executor.scheduleAtFixedRate(::beginEchoCheck, 0L, 1L, TimeUnit.SECONDS)
*/
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
    protected fun setFailure() = setStatusColor(COLOR_FAILURE)
    protected fun setLoading() = setStatusColor(COLOR_LOADING)

    private fun setStatusColor(color: Int) {
        async_ui {
            statusColor.value = color
        }
    }

    companion object {
        private const val COLOR_LOADING = 0xff666666.toInt()
        private const val COLOR_SUCCESS = 0xff00ff00.toInt()
        private const val COLOR_FAILURE = 0xffff0000.toInt()
    }
}
