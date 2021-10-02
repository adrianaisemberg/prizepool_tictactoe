package com.adrianaisemberg.tictactoe.utils

import android.app.Activity
import android.content.ContextWrapper
import android.graphics.Paint
import android.view.View

fun View.activity(): Activity? {
    var ctx = context
    while (ctx is ContextWrapper) {
        if (ctx is Activity) {
            return ctx
        }
        ctx = (context as ContextWrapper).baseContext
    }
    return null
}

fun createPaint() = Paint().apply { isAntiAlias = true }
