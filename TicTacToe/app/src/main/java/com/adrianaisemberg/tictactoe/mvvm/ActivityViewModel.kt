package com.adrianaisemberg.tictactoe.mvvm

import android.app.Activity
import android.os.Bundle

abstract class ActivityViewModel(protected val activity: Activity) : ViewViewModel {
    open fun onCreate(savedInstanceState: Bundle?) {}
}
