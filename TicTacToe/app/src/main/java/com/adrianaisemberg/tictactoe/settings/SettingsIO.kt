package com.adrianaisemberg.tictactoe.settings

interface SettingsIO : SettingsBase {
    fun getString(key: SettingKey, defaultValue: String? = ""): String?
    fun setString(key: SettingKey, value: String?)

    fun getInt(key: SettingKey, defaultValue: Int = 0): Int
    fun setInt(key: SettingKey, value: Int)

    fun getLong(key: SettingKey, defaultValue: Long = 0): Long
    fun setLong(key: SettingKey, value: Long)

    fun getBoolean(key: SettingKey, defaultValue: Boolean = false): Boolean
    fun setBoolean(key: SettingKey, value: Boolean)
}
