package pl.edu.pja.travelerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import pl.edu.pja.travelerapp.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {
    private val binding by lazy { SettingsActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupSpinners()
        setData()
        binding.button2.setOnClickListener{
            val intent = Intent().apply {
                putExtra("textColor",binding.colorSpinner.selectedItem.toString())
                putExtra("textSize",binding.textSizeSpinner.selectedItem.toString())
                if(binding.radius.text.isNotEmpty())
                {
                    putExtra("radius",binding.radius.text.toString().toInt())
                }
                else
                {
                    putExtra("radius",1)
                }
            }
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }

    private fun setData() {
        if (intent.hasExtra("textColor") && intent.hasExtra("textSize") && intent.hasExtra("radius")) {
            val colArray = resources.getStringArray(R.array.colors)
            binding.colorSpinner.setSelection(colArray.indexOf(intent.getStringExtra("textColor")))
            val sizeArray = resources.getStringArray(R.array.textSizes)
            binding.textSizeSpinner.setSelection(sizeArray.indexOf(intent.getStringExtra("textSize")))
            binding.radius.setText(intent.getIntExtra("radius",1).toString())
        }
    }

    private fun setupSpinners()
    {
        val colors: Spinner = findViewById(R.id.colorSpinner)
        val textSizes: Spinner = findViewById(R.id.textSizeSpinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.colors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            colors.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            this,
            R.array.textSizes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            textSizes.adapter = adapter
        }

    }


}