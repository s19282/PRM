package pl.edu.pja.financialmanager

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.icu.util.Calendar
import android.util.AttributeSet
import android.view.View

class Yaxis(context: Context, attrs: AttributeSet) : View(context, attrs)
{
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 20f
        textSize = 60f
    }

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)
        canvas ?: return
        with(canvas)
        {
            drawLine(width.toFloat() * .2f, 0f,width.toFloat() * .2f, height.toFloat(), paint)
//            for (i in 1..Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH))
//            {
//                drawText(i.toString(), xTmp, height.toFloat() * .55f, paint)
//                xTmp += width.toFloat() * .1f
//            }
        }
    }
}