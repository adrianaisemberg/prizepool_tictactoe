package com.adrianaisemberg.tictactoe.echo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewComponent

@Module
@InstallIn(ViewComponent::class)
object EchoModule {

    @Provides
    fun provideEchoViewModel(): EchoViewModel = EchoViewModel()
}
