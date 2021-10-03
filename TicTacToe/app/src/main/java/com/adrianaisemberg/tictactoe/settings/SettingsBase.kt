package com.adrianaisemberg.tictactoe.settings

interface SettingsBase {
    fun <T> getValue(key: SettingKey, defaultValue: T): T
    fun <T> setValue(key: SettingKey, value: T)
}

abstract class SettingsBaseImpl(private val settingsIO: SettingsIO) : SettingsBase {

    override fun <T> getValue(key: SettingKey, defaultValue: T) =
        settingsIO.getValue(key, defaultValue)

    override fun <T> setValue(key: SettingKey, value: T) = settingsIO.setValue(key, value)
}
