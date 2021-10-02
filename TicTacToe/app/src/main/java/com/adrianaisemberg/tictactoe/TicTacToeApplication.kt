package com.adrianaisemberg.tictactoe

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TicTacToeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
    }
}
