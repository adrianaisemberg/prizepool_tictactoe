package com.adrianaisemberg.tictactoe.service

import com.adrianaisemberg.tictactoe.settings.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideTicTacToeService(settings: Settings): TicTacToeService =
        RetrofitBuilderImpl(settings)
            .build()
            .create(TicTacToeService::class.java)
}
