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

private const val REQUEST_ADD_TRANSFER = 1 //TODO: check why it is used

class MainActivity : AppCompatActivity()
{
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val transactionAdapter by lazy { TransactionAdapter(Shared.transactionList) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Recent transfers"
        setContentView(binding.root)
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

    private fun updateSum()
    {
        var inSum = 0.0
        var outSum = 0.0
        for(transaction in Shared.transactionList)
        {
            if(transaction.type == 0 ) inSum += transaction.amount
            else outSum += transaction.amount
        }
        findViewById<TextView>(R.id.incomesSum).text = inSum.toString()
        findViewById<TextView>(R.id.outcomesSum).text = outSum.toString()

    }

    fun openAddActivity(view: View)
    {
        startActivityForResult(
                Intent(this, AddActivity::class.java),
                REQUEST_ADD_TRANSFER
        )
    }



    fun openChartActivity(view: View)
    {
        startActivity(Intent(this, ChartActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if(requestCode == REQUEST_ADD_TRANSFER && resultCode == Activity.RESULT_OK)
        {
            transactionAdapter.list = Shared.transactionList
            updateSum()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}