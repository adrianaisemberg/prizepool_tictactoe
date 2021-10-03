package com.adrianaisemberg.tictactoe

import android.app.Activity
import com.adrianaisemberg.tictactoe.service.TicTacToeService
import com.adrianaisemberg.tictactoe.settings.Settings
import com.adrianaisemberg.tictactoe.utils.ResourcesReader
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
        settings: Settings,
        resourcesReader: ResourcesReader,
    ): MainActivityViewModel =
        MainActivityViewModel(
            activity = activity,
            service = service,
            settings = settings,
            resourcesReader = resourcesReader,
        )
}
