package com.adrianaisemberg.tictactoe.common

import android.app.Activity
import android.os.Bundle

abstract class ActivityViewModel(protected val activity: Activity) : ViewViewModel {
    open fun onResume() {}
    open fun onPause() {}
    open fun onCreate(savedInstanceState: Bundle?) {}
    open fun onStart() {}
    open fun onDestroy() {}
    open fun onBack(): Boolean = true
}
