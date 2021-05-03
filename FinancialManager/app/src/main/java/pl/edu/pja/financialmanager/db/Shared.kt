package pl.edu.pja.financialmanager.db

import pl.edu.pja.financialmanager.model.Transaction
import java.time.LocalDate

object Shared
{
    val transactionList = mutableListOf(
            Transaction(2500.0,"Store", LocalDate.now().minusDays(30),0,0),
//            Transaction(500.0,"Cinema", LocalDate.now().minusDays(30),1,1),
//            Transaction(600.0,"Car repair shop", LocalDate.now().minusDays(29),2,0),
//            Transaction(800.0,"Taxes", LocalDate.now().minusDays(29),3,1),
//            Transaction(600.0,"Food", LocalDate.now().minusDays(27),4,1),
//            Transaction(400.0,"Food", LocalDate.now().minusDays(26),4,0),
//            Transaction(400.0,"Food", LocalDate.now().minusDays(25),4,1),
//            Transaction(1100.0,"Food", LocalDate.now().minusDays(24),4,1),
//            Transaction(100.0,"Food", LocalDate.now().minusDays(23),4,0),
//            Transaction(150.0,"Food", LocalDate.now().minusDays(22),4,1),
//            Transaction(450.0,"Food", LocalDate.now().minusDays(21),4,1),
    )
}