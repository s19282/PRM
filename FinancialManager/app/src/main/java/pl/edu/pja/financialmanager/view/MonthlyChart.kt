package pl.edu.pja.financialmanager.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.icu.util.Calendar
import android.util.AttributeSet
import android.view.View
import pl.edu.pja.financialmanager.db.Shared
import java.time.LocalDate
import kotlin.math.absoluteValue

class MonthlyChart(context: Context, attrs: AttributeSet) : View(context, attrs)
{
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f
        textSize = 60f
    }
    private val paint2 = Paint().apply {
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
            val all = Shared.transactionList.groupBy { it.date }.values.map {
                var tmp = 0.0
                it.forEach{ t->
                    tmp +=  if(t.type==0) t.amount
                    else t.amount*-1
                }
                tmp
            }

            var minValue: Double = all[0]
            var maxValue: Double = all[0]
            var balance = 0.0

            all.forEach {
                balance+=it
                if (balance>maxValue) maxValue = balance
                if (balance<minValue) minValue = balance
            }
            val heightOf0 = if(maxValue>0 && minValue <0) maxValue/(maxValue+minValue.absoluteValue)
                            else if(maxValue<=0) 0.0
                            else 1.0

            val scaledHeightOf0 = height.toFloat() * .1f + height.toFloat() * .8f * heightOf0.toFloat()

            drawLine(0f, scaledHeightOf0,width.toFloat()+100f, scaledHeightOf0, paint)
            val daysInMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)
            var xTmp = width.toFloat() / daysInMonth
            for (i in 1..daysInMonth)
            {
                drawText(i.toString().plus("\t"), xTmp, scaledHeightOf0 *1.05f, paint)
                xTmp += width.toFloat() / daysInMonth
            }

            val daysToShow = 30
            val dailyTransactions = Shared.transactionList.filter { it.date.isAfter(LocalDate.now().minusDays(daysToShow.toLong())) }.groupBy { it.date }

            balance = 0.0
            var previousY = scaledHeightOf0
            var previousX = 0f
            xTmp = width.toFloat() / daysInMonth
            for (i in 0..daysToShow)
            {
                val actualY = dailyTransactions[LocalDate.now().minusDays(i.toLong())]?.sumBy {
                    var tmp = 0.0
                    tmp +=  if(it.type==0) it.amount
                    else it.amount*-1
                    tmp.toInt()
                }?.toFloat()
                if(actualY == null)
                {
                    val actualX = previousX + xTmp
                    drawLine(previousX,previousY,actualX,previousY,paint2)
                    previousX = actualX
                }
                else
                {
                    balance+=actualY
                    val scaledActualY = height.toFloat() * .1f + height.toFloat() * .8f * ((maxValue-balance)/ (maxValue-minValue)).toFloat()
                    val actualX = previousX + xTmp
                    if(scaledActualY<=scaledHeightOf0) paint2.color = Color.GREEN
                    if(scaledActualY>scaledHeightOf0) paint2.color = Color.RED
                    drawLine(previousX,previousY,actualX,scaledActualY,paint2)

                    previousX = actualX
                    previousY = scaledActualY
                }
            }
        }
    }


}