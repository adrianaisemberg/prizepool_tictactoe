package com.adrianaisemberg.tictactoe.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.adrianaisemberg.tictactoe.service.Game
import com.adrianaisemberg.tictactoe.service.GameboardCell
import com.adrianaisemberg.tictactoe.service.Tile
import com.adrianaisemberg.tictactoe.utils.createPaint

class WinnerLine @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var game: Game? = null
    private var gridSize: Int = 0
    private var lineStartX: Float = 0F
    private var lineEndX: Float = 0F
    private var lineStartY: Float = 0F
    private var lineEndY: Float = 0F

    private val paint = createPaint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 20F
        color = Color.RED
        alpha = 100
    }

    fun setGridSize(gridSize: Int) {
        this.gridSize = gridSize
        invalidate()
    }

    fun setGame(game: Game?) {
        this.game = game
        if (game != null) {
            invalidate()
        }
    }

    private fun areCellsEqual(cells: List<GameboardCell>): Boolean {
        if (cells.isEmpty()) return false
        val tile = cells[0].tile
        if (tile == Tile.Empty) return false
        return cells.all { it.tile == tile }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }

        val game = this.game ?: return

        var winnerFound = false
        val rowHeight = height / gridSize
        val colWidth = width / gridSize

        if (!winnerFound) {
            val rows = game.gameboard.groupBy { it.y }
            val winnerRow = rows.values.firstOrNull { areCellsEqual(it) }
            if (winnerRow != null) {
                lineStartX = colWidth / EDGE_FRACTION
                lineEndX = width - colWidth / EDGE_FRACTION
                lineStartY = rowHeight * winnerRow[0].y + rowHeight / 2F
                lineEndY = lineStartY
                winnerFound = true
            }
        }

        if (!winnerFound) {
            val cols = game.gameboard.groupBy { it.x }
            val winnerCol = cols.values.firstOrNull { areCellsEqual(it) }
            if (winnerCol != null) {
                lineStartX = colWidth * winnerCol[0].x + colWidth / 2F
                lineEndX = lineStartX
                lineStartY = rowHeight / EDGE_FRACTION
                lineEndY = height - rowHeight / EDGE_FRACTION
                winnerFound = true
            }
        }

        if (!winnerFound) {
            val diag1 = game.gameboard.filter { it.x == it.y }
            if (areCellsEqual(diag1)) {
                lineStartX = colWidth / EDGE_FRACTION
                lineEndX = width - colWidth / EDGE_FRACTION
                lineStartY = rowHeight / EDGE_FRACTION
                lineEndY = height - rowHeight / EDGE_FRACTION
                winnerFound = true
            }
        }

        if (!winnerFound) {
            val diag2 = game.gameboard.filter { it.x + it.y == gridSize - 1 }
            if (areCellsEqual(diag2)) {
                lineStartX = width - colWidth / EDGE_FRACTION
                lineEndX = colWidth / EDGE_FRACTION
                lineStartY = rowHeight / EDGE_FRACTION
                lineEndY = height - rowHeight / EDGE_FRACTION
                winnerFound = true
            }
        }

        if (!winnerFound) {
            return
        }

        canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint)
    }

    companion object {
        private const val EDGE_FRACTION = 6F
    }
}
