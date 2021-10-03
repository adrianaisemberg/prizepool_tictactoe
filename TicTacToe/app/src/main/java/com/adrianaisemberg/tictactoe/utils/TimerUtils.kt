package com.adrianaisemberg.tictactoe.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

interface Scheduler {
    fun scheduleNowAtFixedRate(period: Long, action: Action): Timer
    fun cancel(timer: Timer)
}

class SchedulerImpl : Scheduler {
    override fun scheduleNowAtFixedRate(period: Long, action: Action): Timer {
        return Timer().apply {
            scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    action.invoke()
                }

            }, 0L, period)
        }
    }

    override fun cancel(timer: Timer) {
        timer.cancel()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object SchedulerModule {

    @Provides
    fun provideScheduler(): Scheduler = SchedulerImpl()
}