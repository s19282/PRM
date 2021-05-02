package pl.edu.pja.financialmanager

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
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

        val id: Int = (intent.extras?.get("id") ?: -1) as Int
        if(id != -1) fillWithData(id)
//        TODO: find better way to check it
    }

    private fun fillWithData(id: Int) {
        val transaction = Shared.transactionList[id]
        findViewById<EditText>(R.id.place).setText(transaction.place)
        findViewById<EditText>(R.id.amount).setText(transaction.amount.toString())
        findViewById<EditText>(R.id.date).setText(transaction.date.toString())
        findViewById<Spinner>(R.id.category).setSelection(transaction.category.toInt())
        findViewById<Spinner>(R.id.transactionType).setSelection(transaction.type.toInt())
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
//            val type = binding.transactionType.selectedItemId
//            val drawable: Drawable = loadDrawables()[type.toInt()]
            val transaction = Transaction(
                    binding.amount.text.toString().toDouble(),
                    binding.place.text.toString(),
                    LocalDate.parse(binding.date.text.toString()),
                    binding.category.selectedItemId.toInt(),
                    binding.transactionType.selectedItemId.toInt()
//                    drawable
            )
            Shared.transactionList.add(transaction)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}