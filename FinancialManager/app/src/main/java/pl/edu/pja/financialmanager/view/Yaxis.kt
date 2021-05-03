package pl.edu.pja.financialmanager.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import pl.edu.pja.financialmanager.db.Shared
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

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
            var minValue: Double = dailyTransactions[0]
            var maxValue: Double = dailyTransactions[0]
            var balance = 0.0

            dailyTransactions.forEach {
                balance+=it
                if (balance>maxValue) maxValue = balance
                if (balance<minValue) minValue = balance
            }
            val set = 15
            var startY = height.toFloat() * .1f
            val step = height.toFloat() * .8f / set
            val valueStep = (maxValue-minValue).absoluteValue/set
            for(i in 1..set+1)
            {
                drawText(maxValue.toInt().toString(), width.toFloat() * .05f, startY, paint)
                maxValue-=valueStep
                startY+=step
            }
        }
    }
}