package pl.edu.pja.financialmanager.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.financialmanager.R
import pl.edu.pja.financialmanager.db.Shared
import pl.edu.pja.financialmanager.adapter.TransactionAdapter
import pl.edu.pja.financialmanager.databinding.ActivityMainBinding
import java.time.LocalDate

private const val REQUEST_ADD_TRANSFER = 1 //TODO: check why it is used
private const val REQUEST_EDIT_TRANSFER = 2

class MainActivity : AppCompatActivity()
{
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val transactionAdapter by lazy { TransactionAdapter(Shared.transactionList, this) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Recent transfers"
        setContentView(binding.root)
        binding.addNew.setOnClickListener( openAddActivity() )
        setupTransactionList()
        updateSum()
    }

    private fun setupTransactionList()
    {
        binding.transactionList.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun updateSum()
    {
        var inSum = 0.0
        var outSum = 0.0
        for(transaction in Shared.transactionList.filter { it.date.month.equals(LocalDate.now().month) && it.date.year == LocalDate.now().year })
        {
            if(transaction.type == 0 ) inSum += transaction.amount
            else outSum += transaction.amount
        }
        findViewById<TextView>(R.id.incomesSum).text = inSum.toString()
        findViewById<TextView>(R.id.outcomesSum).text = outSum.toString()

    }

    private fun openAddActivity(): View.OnClickListener
    {
        return View.OnClickListener {
            startActivityForResult(
                Intent(this, AddActivity::class.java),
                REQUEST_ADD_TRANSFER
        ) }
    }
    fun openEditActivity( id: Int)
    {
        startActivityForResult(
                Intent(this, AddActivity::class.java).putExtra("id",id),
                REQUEST_EDIT_TRANSFER
        )
    }

    fun openChartActivity(view: View)
    {
        startActivity(Intent(this, ChartActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ADD_TRANSFER && resultCode == Activity.RESULT_OK)
        {
            transactionAdapter.list = Shared.transactionList
            updateSum()
        }
        if(requestCode == REQUEST_EDIT_TRANSFER && resultCode == Activity.RESULT_OK)
        {
            transactionAdapter.list = Shared.transactionList
            updateSum()
        }
    }

}