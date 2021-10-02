package com.adrianaisemberg.tictactoe.settings

import android.content.Context
import androidx.preference.PreferenceManager

class SafeSettingsIOImpl(context: Context) : SettingsIOBase() {

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        initListeners()
    }
}
