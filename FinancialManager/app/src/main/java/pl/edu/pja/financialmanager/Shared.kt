package pl.edu.pja.financialmanager

import pl.edu.pja.financialmanager.model.Transaction
import java.time.LocalDate

object Shared
{
    val transactionList = mutableListOf(
            Transaction(400.0,"Store", LocalDate.now(),0,0),
            Transaction(500.0,"Cinema", LocalDate.now(),1,1),
            Transaction(600.0,"Car repair shop", LocalDate.now().plusDays(1),2,0),
            Transaction(700.0,"Taxes", LocalDate.now().plusDays(1),3,1),
            Transaction(800.0,"Food", LocalDate.now().plusDays(2),4,0)
    )
}