package pl.edu.pja.financialmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.financialmanager.adapter.TransactionAdapter
import pl.edu.pja.financialmanager.databinding.ActivityMainBinding

private const val REQUEST_ADD_TRANSFER = 1

class MainActivity : AppCompatActivity()
{
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val transactionAdapter by lazy { TransactionAdapter(Shared.transactionList) }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTransactionList()
    }

    private fun setupTransactionList() {
        binding.transactionList.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        transactionAdapter.list = Shared.transactionList
    }

    fun openAddActivity(view: View)
    {
        startActivity(Intent(this, AddActivity::class.java))
    }

}