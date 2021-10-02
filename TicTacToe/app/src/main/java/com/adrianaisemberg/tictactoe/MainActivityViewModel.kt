package com.adrianaisemberg.tictactoe

import android.app.Activity
import android.os.Bundle
import com.adrianaisemberg.tictactoe.common.ActivityViewModel
import com.adrianaisemberg.tictactoe.common.Common
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.settings.Settings
import com.adrianaisemberg.tictactoe.utils.async_io

class MainActivityViewModel(
    activity: Activity,
    private val service: TicTacToeService,
    private val common: Common,
) : ActivityViewModel(activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKey()
    }

    private fun loadKey() {
        if (!common.settings.authenticationKey.isNullOrEmpty()) {
            return
        }

        async_io {
            service.getKey().enqueue(
                onResponse = { response ->
                    common.settings.authenticationKey = response.body()
                },
                onFailure = { t ->

                }
            )
        }
    }
}
