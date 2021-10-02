package com.adrianaisemberg.tictactoe.settings

import android.content.Context
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
    ): Settings =
        SettingsImpl(
            settingsIO = SettingsIOWrapper(
                encryptedSettingsIO = EncryptedSettingsIOImpl(context),
            ),
        )
}
