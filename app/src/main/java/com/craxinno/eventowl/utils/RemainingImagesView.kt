package com.craxinno.eventowl.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class RemainingImagesView (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
        ) : View(context, attrs, defStyleAttr) {
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED // Replace with your desired color
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 32f // Replace with your desired text size
        color = Color.WHITE // Replace with your desired text color
        textAlign = Paint.Align.CENTER
    }

    private var remainingImagesCount = 0

    fun setRemainingImages(remainingCount: Int) {
        remainingImagesCount = remainingCount
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()
        val radius = width.coerceAtMost(height) / 2

        canvas.drawCircle(width / 2, height / 2, radius, circlePaint)

        val xPos = width / 2
        val yPos = height / 2 - (textPaint.descent() + textPaint.ascent()) / 2
        canvas.drawText("+${remainingImagesCount}", xPos, yPos, textPaint)
    }
}