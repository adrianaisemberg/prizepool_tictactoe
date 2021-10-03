package com.adrianaisemberg.tictactoe.echo

import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.utils.Scheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent

@Module
@InstallIn(ViewComponent::class)
object EchoModule {

    @Provides
    fun provideEchoViewModel(
        ticTacToeService: TicTacToeService,
        scheduler: Scheduler,
    ): EchoViewModel = EchoViewModel(
        service = ticTacToeService,
        scheduler = scheduler,
    )

    @Provides
    fun provideEchoAuthViewModel(
        ticTacToeService: TicTacToeService,
        scheduler: Scheduler,
    ): EchoAuthViewModel = EchoAuthViewModel(
        service = ticTacToeService,
        scheduler = scheduler,
    )
}
