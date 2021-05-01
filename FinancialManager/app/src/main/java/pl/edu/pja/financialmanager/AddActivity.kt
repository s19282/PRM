package pl.edu.pja.financialmanager

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
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

    private fun loadDrawables(): List<Drawable>
    {
        val drawableIds = listOf(
                R.drawable.income,
                R.drawable.outcome
        )
        return drawableIds.map { resources.getDrawable(it, theme) }
    }

    private fun setupSave()
    {
        binding.saveButton.setOnClickListener {
            val type = binding.transactionType.selectedItemId
            val transaction = Transaction(
                    binding.amount.text.toString().toDouble(),
                    binding.place.text.toString(),
                    LocalDate.parse(binding.date.text.toString()),
                    binding.category.selectedItem.toString(),
                    type,
                    if (type == 0L) loadDrawables()[0] else loadDrawables()[1]
            )
            Shared.transactionList.add(transaction)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}