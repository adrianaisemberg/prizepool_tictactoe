package com.adrianaisemberg.tictactoe

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * an application is needed to trigger Hilt
 */
@HiltAndroidApp
class TicTacToeApplication : Application()
