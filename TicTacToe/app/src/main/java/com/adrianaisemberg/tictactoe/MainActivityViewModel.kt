package com.adrianaisemberg.tictactoe

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.mvvm.ActivityViewModel
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.service.Winner
import com.adrianaisemberg.tictactoe.service.enqueue
import com.adrianaisemberg.tictactoe.settings.Settings
import com.adrianaisemberg.tictactoe.utils.ResourcesReader
import com.adrianaisemberg.tictactoe.utils.async_io

class MainActivityViewModel(
    activity: Activity,
    private val service: TicTacToeService,
    private val settings: Settings,
    private val resourcesReader: ResourcesReader,
) : ActivityViewModel(activity), GameUpdateListener {

    val game = MutableLiveData<Game>()
    val gameStatusMessage = MutableLiveData("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKeyAndCurrentGame()
    }

    /**
     * either fetch a new auth-key or load the stored one and start a new game
     */
    private fun loadKeyAndCurrentGame() {
        // if there's an auth key - load the current game
        if (!settings.authenticationKey.isNullOrEmpty()) {
            loadCurrentGame()
            return
        }

        async_io {
            // fetch an auth key and store locally
            service.getKey().enqueue { response ->
                settings.authenticationKey = response.body()
                loadCurrentGame()
            }
        }
    }

    /**
     * loads the current game from the backend
     */
    private fun loadCurrentGame() {
        // if there's no active game - start a new one
        val lastGameId = settings.lastGameId
        if (lastGameId.isNullOrEmpty()) {
            startNewGame()
            return
        }

        async_io {
            // fetch the current active game and load it
            service.getGame(lastGameId).enqueue { response ->
                val body = response.body()
                game.value = body
            }
        }
    }

    /**
     * starts a new game
     */
    fun startNewGame() {
        async_io {
            service.postGame().enqueue { response ->
                val body = response.body()
                settings.lastGameId = body?.gameId
                game.value = body
            }
        }
    }

    /**
     * the game was updated - check for a winner
     */
    override fun onGameUpdated(game: Game) {
        gameStatusMessage.value = when (game.winner) {
            Winner.None -> ""
            Winner.X -> resourcesReader.getString(R.string.winner, "X")
            Winner.O -> resourcesReader.getString(R.string.winner, "O")
            Winner.Tie -> resourcesReader.getString(R.string.tie)
        }
    }
}
