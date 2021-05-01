package pl.edu.pja.financialmanager

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.icu.util.Calendar
import android.util.AttributeSet
import android.view.View

class MonthlyChart(context: Context, attrs: AttributeSet) : View(context, attrs)
{
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f
        textSize = 60f
    }

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)
        canvas ?: return
        with(canvas)
        {
            drawLine(0f, height.toFloat() * .5f,width.toFloat()+100f, height.toFloat() * .5f, paint)
            val daysInMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)
            var xTmp = width.toFloat() / daysInMonth
            for (i in 1..daysInMonth)
            {
                drawText(i.toString().plus("\t"), xTmp, height.toFloat() * .55f, paint)
                xTmp += width.toFloat() / daysInMonth
            }
        }
    }
}