package com.adrianaisemberg.tictactoe.echo

import androidx.lifecycle.MutableLiveData
import com.adrianaisemberg.tictactoe.api.TicTacToeService
import com.adrianaisemberg.tictactoe.common.ViewViewModel
import com.adrianaisemberg.tictactoe.utils.async_io
import com.adrianaisemberg.tictactoe.utils.async_ui
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EchoViewModel(
    private val service: TicTacToeService,
) : ViewViewModel {

    val echoResponse = MutableLiveData<String>()

    override fun onFinishInflate() {
        super.onFinishInflate()

        beginEchoCheck()
    }

    private fun beginEchoCheck() {
        async_io {
            service.getEcho().enqueue(object : Callback<String> {

                // TODO: create extension for this crap
                
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    async_ui {
                        echoResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    async_ui {
                        echoResponse.value = t.message
                    }
                }
            })
        }
    }
}
