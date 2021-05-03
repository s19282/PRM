package pl.edu.pja.financialmanager.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import pl.edu.pja.financialmanager.R
import pl.edu.pja.financialmanager.db.Shared
import pl.edu.pja.financialmanager.databinding.ActivityAddBinding
import pl.edu.pja.financialmanager.model.Transaction
import java.time.LocalDate

class AddActivity : AppCompatActivity()
{
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Add transfer"
        setContentView(binding.root)
        setupSave()
        setupSpinners()

        if(intent.hasExtra("id"))   fillWithData(intent.extras?.get("id") as Int)
    }

    private fun fillWithData(id: Int) {
        val transaction = Shared.transactionList[id]
        findViewById<EditText>(R.id.place).setText(transaction.place)
        findViewById<EditText>(R.id.amount).setText(transaction.amount.toString())
        findViewById<EditText>(R.id.date).setText(transaction.date.toString())
        findViewById<Spinner>(R.id.category).setSelection(transaction.category)
        findViewById<Spinner>(R.id.transactionType).setSelection(transaction.type)
    }

    private fun setupSpinners()
    {
        val categories: Spinner = findViewById(R.id.category)
        val transferTypes: Spinner = findViewById(R.id.transactionType)

        ArrayAdapter.createFromResource(
                this,
                R.array.categories,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categories.adapter = adapter
        }
        ArrayAdapter.createFromResource(
                this,
                R.array.transferTypes,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            transferTypes.adapter = adapter
        }

    }

    private fun setupSave()
    {
        binding.saveButton.setOnClickListener {
            if(intent.hasExtra("id"))
            {
                val transaction = Shared.transactionList.get(intent.extras?.get("id") as Int)
                transaction.apply {
                    amount = binding.amount.text.toString().toDouble()
                    place = binding.place.text.toString()
                    date = LocalDate.parse(binding.date.text.toString())
                    category = binding.category.selectedItemId.toInt()
                    type = binding.transactionType.selectedItemId.toInt()
                }
            }
            else
            {
                val transaction = Transaction(
                        binding.amount.text.toString().toDouble(),
                        binding.place.text.toString(),
                        LocalDate.parse(binding.date.text.toString()),
                        binding.category.selectedItemId.toInt(),
                        binding.transactionType.selectedItemId.toInt()
                )
                Shared.transactionList.add(transaction)
            }
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}