package com.adrianaisemberg.tictactoe.settings

class SettingsIOWrapper(
    private val encryptedSettingsIO: SettingsIO,
) : SettingsIO {

    override fun getString(key: SettingKey, defaultValue: String?): String? =
        encryptedSettingsIO.getString(key, defaultValue)

    override fun setString(key: SettingKey, value: String?) =
        encryptedSettingsIO.setString(key, value)

    override fun getInt(key: SettingKey, defaultValue: Int): Int =
        encryptedSettingsIO.getInt(key, defaultValue)

    override fun setInt(key: SettingKey, value: Int) = encryptedSettingsIO.setInt(key, value)

    override fun getLong(key: SettingKey, defaultValue: Long): Long =
        encryptedSettingsIO.getLong(key, defaultValue)

    override fun setLong(key: SettingKey, value: Long) = encryptedSettingsIO.setLong(key, value)

    override fun getBoolean(key: SettingKey, defaultValue: Boolean): Boolean =
        encryptedSettingsIO.getBoolean(key, defaultValue)

    override fun setBoolean(key: SettingKey, value: Boolean) =
        encryptedSettingsIO.setBoolean(key, value)

    override fun <T> getValue(key: SettingKey, defaultValue: T): T =
        encryptedSettingsIO.getValue(key, defaultValue)

    override fun <T> setValue(key: SettingKey, value: T) = encryptedSettingsIO.setValue(key, value)
}
