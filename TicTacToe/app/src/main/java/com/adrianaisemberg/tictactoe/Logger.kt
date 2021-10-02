package com.adrianaisemberg.tictactoe

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


interface Logger {
    fun d(message: String, tr: Throwable? = null): Int
    fun v(message: String, tr: Throwable? = null): Int
    fun w(message: String, tr: Throwable? = null): Int
}

class LoggerImpl : Logger {
    companion object {
        private const val TAG = "TIC_TAC_TOE"
    }

    override fun d(message: String, tr: Throwable?) = Log.d(TAG, message, tr)
    override fun v(message: String, tr: Throwable?) = Log.v(TAG, message, tr)
    override fun w(message: String, tr: Throwable?) = Log.w(TAG, message, tr)
}

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {
    @Provides
    fun provideLogger(): Logger = LoggerImpl()
}
