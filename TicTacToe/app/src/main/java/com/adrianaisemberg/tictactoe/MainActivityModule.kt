package com.adrianaisemberg.tictactoe

import android.app.Activity
import com.adrianaisemberg.tictactoe.common.Common
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class, SingletonComponent::class)
object MainActivityModule {

    @Provides
    fun provideMainActivityViewModel(
        activity: Activity,
        service: TicTacToeService,
        common: Common,
    ): MainActivityViewModel =
        MainActivityViewModel(
            activity = activity,
            service = service,
            common = common,
        )
}
