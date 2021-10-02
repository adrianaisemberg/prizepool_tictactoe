package com.adrianaisemberg.tictactoe.utils

import java.util.*

fun Timer.scheduleNowAtFixedRate(period: Long, action: Action) {
    scheduleAtFixedRate(object : TimerTask() {
        override fun run() {
            action.invoke()
        }

    }, 0L, period)
}
