package com.adrianaisemberg.tictactoe.api

import retrofit2.Call
import retrofit2.http.GET

interface TicTacToeService {

    @GET("/echo")
    fun getEcho(): Call<String>
}