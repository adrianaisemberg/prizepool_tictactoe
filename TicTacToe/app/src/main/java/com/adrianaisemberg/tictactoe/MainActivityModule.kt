package com.adrianaisemberg.tictactoe

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class, SingletonComponent::class)
object MainActivityModule {

    @Provides
    fun provideMainActivityViewModel(activity: Activity): MainActivityViewModel =
        MainActivityViewModel(activity)
}
