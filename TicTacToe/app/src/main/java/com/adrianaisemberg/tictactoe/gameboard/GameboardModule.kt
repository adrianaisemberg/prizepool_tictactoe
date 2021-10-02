package com.adrianaisemberg.tictactoe.gameboard

import com.adrianaisemberg.tictactoe.service.TicTacToeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object GameboardModule {

    @Provides
    fun provideGameboardViewModel(
        service: TicTacToeService,
    ): GameboardViewModel = GameboardViewModel(
        service = service,
    )

    @Provides
    fun provideGameboardCellViewModel(): GameboardCellViewModel = GameboardCellViewModel()
}
