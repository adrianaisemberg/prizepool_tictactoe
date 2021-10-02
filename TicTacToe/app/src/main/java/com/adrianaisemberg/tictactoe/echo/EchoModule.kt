package com.adrianaisemberg.tictactoe.echo

import com.adrianaisemberg.tictactoe.service.TicTacToeService
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
    ): EchoViewModel = EchoViewModel(
        service = ticTacToeService,
    )

    @Provides
    fun provideEchoAuthViewModel(
        ticTacToeService: TicTacToeService,
    ): EchoAuthViewModel = EchoAuthViewModel(
        service = ticTacToeService,
    )
}
