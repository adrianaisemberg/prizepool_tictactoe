package com.adrianaisemberg.tictactoe.common

import com.adrianaisemberg.tictactoe.settings.Settings
import com.adrianaisemberg.tictactoe.utils.Logger
import com.adrianaisemberg.tictactoe.utils.ResourcesReader

interface Common {
    val logger: Logger
    val settings: Settings
    val resourcesReader: ResourcesReader
//    val analytics: Analytics
//    val remoteConfig: RemoteConfig
}

class CommonImpl(
    override val logger: Logger,
    override val settings: Settings,
    override val resourcesReader: ResourcesReader,
//    override val analytics: Analytics,
//    override val remoteConfig: RemoteConfig,
) : Common
