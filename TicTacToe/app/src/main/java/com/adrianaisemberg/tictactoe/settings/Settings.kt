package com.adrianaisemberg.tictactoe.settings

import com.adrianaisemberg.tictactoe.utils.ResourcesReader

interface Settings : SettingsBase {
}

class SettingsImpl(
    private val resourcesReader: ResourcesReader,
    private val settingsIO: SettingsIO,
) : SettingsBaseImpl(settingsIO),
    Settings {
}
