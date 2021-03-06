package com.adrianaisemberg.tictactoe.settings

interface Settings : SettingsBase {
    var authenticationKey: String?
    var lastGameId: String?
}

class SettingsImpl(
    private val settingsIO: SettingsIO,
) : SettingsBaseImpl(settingsIO), Settings {

    override var authenticationKey: String?
        get() = settingsIO.getString(SettingKey.AuthenticationKey)
        set(value) = settingsIO.setString(SettingKey.AuthenticationKey, value)

    override var lastGameId: String?
        get() = settingsIO.getString(SettingKey.LastGameId)
        set(value) = settingsIO.setString(SettingKey.LastGameId, value)
}
