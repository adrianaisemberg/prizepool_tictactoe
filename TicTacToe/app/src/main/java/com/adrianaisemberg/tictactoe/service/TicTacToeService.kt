package com.adrianaisemberg.tictactoe.service

import retrofit2.Call
import retrofit2.http.*

interface TicTacToeService {

    @GET("/key")
    fun getKey(): Call<String>

    @GET("/echo")
    fun getEcho(): Call<String>

    @GET("/echo/auth")
    fun getEchoAuth(): Call<String>

    @POST("/game")
    fun postGame(): Call<GameResponse>

    @GET("/game/{game_id}")
    fun getGame(
        @Path("game_id") gameId: String,
    ): Call<GameResponse>

    @PUT("/game/{game_id}/move")
    fun putGameMove(
        @Path("game_id") gameId: String,
        @Query("x") x: Int,
        @Query("y") y: Int,
        @Query("tile") tile: Tile,
    ): Call<GameResponse>
}