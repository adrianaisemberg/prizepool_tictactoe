package com.adrianaisemberg.tictactoe

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.common.ActivityViewModel
import com.adrianaisemberg.tictactoe.common.Common
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.Winner
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.utils.async_io

class MainActivityViewModel(
    activity: Activity,
    private val service: TicTacToeService,
    private val common: Common,
) : ActivityViewModel(activity), GameUpdateListener {

    val game = MutableLiveData<Game>()
    val gameStatusMessage = MutableLiveData("")

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
            service.getKey().enqueue { response ->
                common.settings.authenticationKey = response.body()
                loadCurrentGame()
            }
        }
    }

    private fun loadCurrentGame() {
        val lastGameId = common.settings.lastGameId
        if (lastGameId.isNullOrEmpty()) {
            startNewGame()
            return
        }

        async_io {
            service.getGame(lastGameId).enqueue { response ->
                val body = response.body()
                game.value = body
            }
        }
    }

    fun startNewGame() {
        async_io {
            service.postGame().enqueue { response ->
                val body = response.body()
                common.settings.lastGameId = body?.gameId
                game.value = body
            }
        }
    }

    override fun onGameUpdated(game: Game) {
        gameStatusMessage.value = when (game.winner) {
            Winner.None -> ""
            Winner.X -> common.resourcesReader.getString(R.string.winner, "X")
            Winner.O -> common.resourcesReader.getString(R.string.winner, "O")
            Winner.Tie -> common.resourcesReader.getString(R.string.tie)
        }
    }
}
