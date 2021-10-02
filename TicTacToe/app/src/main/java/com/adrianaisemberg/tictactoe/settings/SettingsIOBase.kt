package com.adrianaisemberg.tictactoe.settings

import android.content.SharedPreferences
import androidx.core.content.edit
import com.adrianaisemberg.tictactoe.utils.mutableListMapOf

open class SettingsIOBase : SettingsIO {
    protected lateinit var sharedPreferences: SharedPreferences

    private val keyListeners = mutableListMapOf<SettingKey, SettingsChangedListener>()

    private lateinit var mainListener: SharedPreferences.OnSharedPreferenceChangeListener

    protected fun initListeners() {
        mainListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            val settingKey = SettingKey.valueOf(key)

            keyListeners[settingKey].forEach { it.onSettingsChanged(settingKey) }
        }

        sharedPreferences.registerOnSharedPreferenceChangeListener(mainListener)
    }

    override fun addListener(
        key: SettingKey,
        invokeNow: Boolean,
        listener: SettingsChangedListener
    ) {
        keyListeners[key] = listener

        if (invokeNow) {
            listener.onSettingsChanged(key)
        }
    }

    override fun addListener(
        keys: Array<SettingKey>,
        invokeNow: Boolean,
        listener: SettingsChangedListener
    ) {
        keys.forEach {
            addListener(it, false, listener)
        }

        if (invokeNow) {
            listener.onSettingsChanged(SettingKey._Any)
        }
    }

    override fun removeListener(key: SettingKey, listener: SettingsChangedListener) {
        keyListeners[key].remove(listener)
    }

    override fun removeListener(keys: Array<SettingKey>, listener: SettingsChangedListener) {
        keys.forEach { removeListener(it, listener) }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> getValue(key: SettingKey, defaultValue: T): T {
        return sharedPreferences.all[key.name] as T ?: defaultValue
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> setValue(key: SettingKey, value: T) {
        sharedPreferences.edit {
            when (value) {
                is String -> putString(key.name, value)
                is Int -> putInt(key.name, value)
                is Long -> putLong(key.name, value)
                is Boolean -> putBoolean(key.name, value)
                is Float -> putFloat(key.name, value)
                is Set<*> -> putStringSet(key.name, value as Set<String>)
            }
        }
    }

    override fun getString(key: SettingKey, defaultValue: String?) =
        sharedPreferences.getString(key.name, defaultValue)

    override fun setString(key: SettingKey, value: String?) =
        sharedPreferences.edit { this.putString(key.name, value) }


    override fun getInt(key: SettingKey, defaultValue: Int): Int =
        sharedPreferences.getInt(key.name, defaultValue)

    override fun setInt(key: SettingKey, value: Int) =
        sharedPreferences.edit { putInt(key.name, value) }


    override fun getLong(key: SettingKey, defaultValue: Long): Long =
        sharedPreferences.getLong(key.name, defaultValue)

    override fun setLong(key: SettingKey, value: Long) =
        sharedPreferences.edit { putLong(key.name, value) }


    override fun getBoolean(key: SettingKey, defaultValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key.name, defaultValue)

    override fun setBoolean(key: SettingKey, value: Boolean) =
        sharedPreferences.edit { putBoolean(key.name, value) }
}
