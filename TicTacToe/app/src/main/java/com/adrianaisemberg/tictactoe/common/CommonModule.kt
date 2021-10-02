package com.adrianaisemberg.tictactoe.common

import com.adrianaisemberg.tictactoe.settings.Settings
import com.adrianaisemberg.tictactoe.utils.Logger
import com.adrianaisemberg.tictactoe.utils.ResourcesReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    fun provideCommon(
        logger: Logger,
        settings: Settings,
        resourcesReader: ResourcesReader,
    ): Common = CommonImpl(
        logger = logger,
        settings = settings,
        resourcesReader = resourcesReader,
    )
}
