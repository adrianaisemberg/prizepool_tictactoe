package com.adrianaisemberg.tictactoe.settings

class SettingsIOWrapper(
    private val safeSettingsIO: SettingsIO,
    private val encryptedSettingsIO: SettingsIO,
) : SettingsIO {

    override fun getString(key: SettingKey, defaultValue: String?): String? {
        return if (key.encrypt)
            encryptedSettingsIO.getString(key, defaultValue)
        else
            safeSettingsIO.getString(key, defaultValue)
    }

    override fun setString(key: SettingKey, value: String?) {
        if (key.encrypt) {
            encryptedSettingsIO.setString(key, value)
        } else {
            safeSettingsIO.setString(key, value)
        }
    }

    override fun getInt(key: SettingKey, defaultValue: Int): Int {
        return if (key.encrypt)
            encryptedSettingsIO.getInt(key, defaultValue)
        else
            safeSettingsIO.getInt(key, defaultValue)
    }

    override fun setInt(key: SettingKey, value: Int) {
        if (key.encrypt) {
            encryptedSettingsIO.setInt(key, value)
        } else {
            safeSettingsIO.setInt(key, value)
        }
    }

    override fun getLong(key: SettingKey, defaultValue: Long): Long {
        return if (key.encrypt)
            encryptedSettingsIO.getLong(key, defaultValue)
        else
            safeSettingsIO.getLong(key, defaultValue)
    }

    override fun setLong(key: SettingKey, value: Long) {
        if (key.encrypt) {
            encryptedSettingsIO.setLong(key, value)
        } else {
            safeSettingsIO.setLong(key, value)
        }
    }

    override fun getBoolean(key: SettingKey, defaultValue: Boolean): Boolean {
        return if (key.encrypt)
            encryptedSettingsIO.getBoolean(key, defaultValue)
        else
            safeSettingsIO.getBoolean(key, defaultValue)
    }

    override fun setBoolean(key: SettingKey, value: Boolean) {
        if (key.encrypt) {
            encryptedSettingsIO.setBoolean(key, value)
        } else {
            safeSettingsIO.setBoolean(key, value)
        }
    }

    override fun <T> getValue(key: SettingKey, defaultValue: T): T {
        return if (key.encrypt)
            encryptedSettingsIO.getValue(key, defaultValue)
        else
            safeSettingsIO.getValue(key, defaultValue)
    }

    override fun <T> setValue(key: SettingKey, value: T) {
        if (key.encrypt) {
            encryptedSettingsIO.setValue(key, value)
        } else {
            safeSettingsIO.setValue(key, value)
        }
    }

    override fun addListener(
        key: SettingKey,
        invokeNow: Boolean,
        listener: SettingsChangedListener
    ) {
        if (key.encrypt) {
            encryptedSettingsIO.addListener(key, invokeNow, listener)
        } else {
            safeSettingsIO.addListener(key, invokeNow, listener)
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
        if (key.encrypt) {
            encryptedSettingsIO.removeListener(key, listener)
        } else {
            safeSettingsIO.removeListener(key, listener)
        }
    }

    override fun removeListener(keys: Array<SettingKey>, listener: SettingsChangedListener) {
        keys.forEach {
            removeListener(it, listener)
        }
    }
}
