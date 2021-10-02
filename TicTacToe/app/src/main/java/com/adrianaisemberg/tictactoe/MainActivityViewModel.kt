package com.adrianaisemberg.tictactoe

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.common.ActivityViewModel
import com.adrianaisemberg.tictactoe.common.Common
import com.adrianaisemberg.tictactoe.service.GameResponse
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.utils.async_io

class MainActivityViewModel(
    activity: Activity,
    private val service: TicTacToeService,
    private val common: Common,
) : ActivityViewModel(activity) {

    val currentGame = MutableLiveData<GameResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKeyAndCurrentGame()
    }

    private fun loadKeyAndCurrentGame() {
        if (!common.settings.authenticationKey.isNullOrEmpty()) {
            loadCurrentGame()
            return
        }

        async_io {
            service.getKey().enqueue(
                onResponse = { response ->
                    common.settings.authenticationKey = response.body()
                    loadCurrentGame()
                },
                onFailure = { t ->

                }
            )
        }
    }

    private fun loadCurrentGame() {
        val lastGameId = common.settings.lastGameId
        if (lastGameId.isNullOrEmpty()) {
            return
        }

        async_io {
            service.getGame(lastGameId).enqueue(
                onResponse = { response ->
                    val body = response.body()
                    currentGame.value = body
                },
                onFailure = { t ->

                }
            )
        }
    }

    fun startNewGame() {
        async_io {
            service.postGame().enqueue(
                onResponse = { response ->
                    val body = response.body()
                    common.settings.lastGameId = body?.gameId
                    currentGame.value = body
                },
                onFailure = { t ->

                }
            )
        }
    }
}
