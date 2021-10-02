package com.adrianaisemberg.tictactoe.settings

import android.content.Context
import com.adrianaisemberg.tictactoe.utils.ResourcesReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettings(
        @ApplicationContext context: Context,
        resourcesReader: ResourcesReader,
    ): Settings =
        SettingsImpl(
            resourcesReader = resourcesReader,
            settingsIO = SettingsIOWrapper(
                safeSettingsIO = SafeSettingsIOImpl(context),
                encryptedSettingsIO = EncryptedSettingsIOImpl(context),
            ),
        )
}
