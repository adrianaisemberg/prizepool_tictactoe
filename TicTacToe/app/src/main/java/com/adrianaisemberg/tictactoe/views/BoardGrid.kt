package com.adrianaisemberg.tictactoe.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.adrianaisemberg.tictactoe.utils.createPaint

class BoardGrid @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var gridSize: Int = 0

    private val paint = createPaint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
        color = Color.BLACK
    }

    fun setGridSize(gridSize: Int) {
        this.gridSize = gridSize
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }

        val count = gridSize - 1
        val rowHeight = height / gridSize
        val colWidth = width / gridSize

        // draw horizontal lines
        for (i in 1..count) {
            val startX = 0F
            val endX = width.toFloat()
            val y = (rowHeight * i).toFloat()
            canvas.drawLine(startX, y, endX, y, paint)
        }

        // draw vertical lines
        for (i in 1..count) {
            val startY = 0F
            val endY = height.toFloat()
            val x = (colWidth * i).toFloat()
            canvas.drawLine(x, startY, x, endY, paint)
        }
    }
}
