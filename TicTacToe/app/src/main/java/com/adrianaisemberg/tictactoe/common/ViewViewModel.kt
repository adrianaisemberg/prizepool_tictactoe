package com.adrianaisemberg.tictactoe.common

import android.view.View

interface ViewViewModel {
    fun onFinishInflate() {}
    fun setOnClickListener(listener: View.OnClickListener?) {}
    fun onDetachedFromWindow() {}
}
