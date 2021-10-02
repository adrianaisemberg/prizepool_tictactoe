package com.adrianaisemberg.tictactoe.settings

interface SettingsBase {
    fun <T> getValue(key: SettingKey, defaultValue: T): T
    fun <T> setValue(key: SettingKey, value: T)

    fun addListener(key: SettingKey, listener: SettingsChangedListener)
    fun addListener(
        keys: Array<SettingKey>,
        listener: SettingsChangedListener
    )

    fun removeListener(key: SettingKey, listener: SettingsChangedListener)
    fun removeListener(keys: Array<SettingKey>, listener: SettingsChangedListener)
}

abstract class SettingsBaseImpl(private val settingsIO: SettingsIO) : SettingsBase {

    override fun <T> getValue(key: SettingKey, defaultValue: T) =
        settingsIO.getValue(key, defaultValue)

    override fun <T> setValue(key: SettingKey, value: T) = settingsIO.setValue(key, value)

    override fun addListener(
        key: SettingKey,
        listener: SettingsChangedListener
    ) = settingsIO.addListener(
        key,
        listener
    )

    override fun addListener(
        keys: Array<SettingKey>,
        listener: SettingsChangedListener
    ) = settingsIO.addListener(
        keys,
        listener
    )

    override fun removeListener(key: SettingKey, listener: SettingsChangedListener) =
        settingsIO.removeListener(key, listener)

    override fun removeListener(keys: Array<SettingKey>, listener: SettingsChangedListener) =
        settingsIO.removeListener(keys, listener)
}

interface SettingsChangedListener {
    fun onSettingsChanged(key: SettingKey)
}
