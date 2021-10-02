package com.adrianaisemberg.tictactoe

import android.view.View

interface ViewViewModel {
    fun onFinishInflate() {}
    fun setOnClickListener(listener: View.OnClickListener?) {}
    fun onDetachedFromWindow() {}
}
