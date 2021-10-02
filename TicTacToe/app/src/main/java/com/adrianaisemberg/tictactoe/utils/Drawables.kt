package com.adrianaisemberg.tictactoe.utils

import android.graphics.*
import android.graphics.drawable.Drawable

object Drawables {

    fun boardLines(gridSize: Int = 3) = drawable { canvas ->
        val paint = createPaint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 3F
            color = Color.BLACK
        }

        val count = gridSize - 1
        val rowHeight = canvas.height / gridSize
        val rowWidth = canvas.width / gridSize

        for (i in 1..count) {
            val startX = 0F
            val endX = canvas.width.toFloat()
            val y = (rowHeight * i).toFloat()
            canvas.drawLine(startX, y, endX, y, paint)
        }

        for (i in 1..count) {
            val startY = 0F
            val endY = canvas.height.toFloat()
            val x = (rowWidth * i).toFloat()
            canvas.drawLine(x, startY, x, endY, paint)
        }
    }

    private fun drawable(drawer: ActionOf<Canvas>) = OpaqueDrawable(drawer)

    class OpaqueDrawable internal constructor(private val drawer: ActionOf<Canvas>) : Drawable() {
        override fun draw(canvas: Canvas) = drawer.invoke(canvas)
        override fun setAlpha(i: Int) {}
        override fun setColorFilter(colorFilter: ColorFilter?) {}
        override fun getOpacity() = PixelFormat.OPAQUE
    }

    private const val ROUND_BORDER_RADIUS = 1000F
}