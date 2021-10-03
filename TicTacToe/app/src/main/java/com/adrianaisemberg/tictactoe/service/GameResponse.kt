package com.adrianaisemberg.tictactoe.service

import com.google.gson.annotations.SerializedName

data class Game(

    /**
     * Game ID that will be utilized for interacting with the created game
     */
    @SerializedName("game_id")
    var gameId: String,

    /**
     * NONE, X, O, TIE
     */
    @SerializedName("winner")
    var winner: Winner,

    /**
     * NONE, ACTIVE, COMPLETE
     */
    @SerializedName("status")
    var status: Status,

    /**
     * List of all the tiles of the gameboard.  See GameboardResponse Object
     */
    @SerializedName("gameboard")
    var gameboard: List<GameboardCell>,
)

data class GameboardCell(

    /**
     * x coordinate of tile as integer
     */
    @SerializedName("x")
    var x: Int,

    /**
     * y coordinate of tile as integer
     */
    @SerializedName("y")
    var y: Int,

    /**
     * EMPTY, X, O
     */
    @SerializedName("tile")
    var tile: Tile,
)

enum class Winner {

    @SerializedName("NONE")
    None,

    @SerializedName("X")
    X,

    @SerializedName("O")
    O,

    @SerializedName("TIE")
    Tie,
}

enum class Status {

    @SerializedName("NONE")
    None,

    @SerializedName("ACTIVE")
    Active,

    @SerializedName("COMPLETE")
    Complete,
}

enum class Tile {

    @SerializedName("EMPTY")
    Empty,

    @SerializedName("X")
    X,

    @SerializedName("O")
    O,
}
