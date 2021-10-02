package com.adrianaisemberg.tictactoe.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.adrianaisemberg.tictactoe.utils.createPaint

class CircleFillView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paintFill = createPaint().apply {
        style = Paint.Style.FILL
    }

    fun setFillColor(color: Int) {
        paintFill.color = color
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }

        val radius = (width / 2).toFloat()

        canvas.drawCircle(radius, radius, radius, paintFill)
    }
}
