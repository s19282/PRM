package pl.edu.pja.financialmanager

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.icu.util.Calendar
import android.util.AttributeSet
import android.view.View
import java.time.LocalDate
import kotlin.math.absoluteValue

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
            val dailyTransactions = Shared.transactionList.groupBy { it.date }.values.map {
                var tmp = 0.0
                it.forEach{ t->
                    tmp +=  if(t.type==0) t.amount
                            else t.amount*-1
                }
                tmp
            }

            val minValue: Double = dailyTransactions.minOf { it }
            var maxValue: Double = dailyTransactions.maxOf { it }
            val set = 15
            var startY = height.toFloat() * .1f
            val step = height.toFloat() * .8f / set
            val valueStep = range(maxValue,minValue)/set
            for(i in 1..set+1)
            {
                drawText(maxValue.toInt().toString(), width.toFloat() * .05f, startY, paint)
                maxValue-=valueStep
                startY+=step
            }
        }
    }

    private fun range(v1: Double, v2: Double): Double {
        if(v1>=0 && v2 >=0)
        {
            return  if(v1>=v2)  v1-v2
                    else        v2-v1
        }
        if(v1 < 0 && v2 < 0)
        {
            return  if(v1>=v2)  v2.absoluteValue-v1.absoluteValue
                    else        v2.absoluteValue-v1.absoluteValue
        }
        if(v1 > 0 && v2 < 0)
            return v1 + v2.absoluteValue
        if(v2 > 0 && v1 < 0)
            return v2 + v1.absoluteValue
        return 0.0
    }
}