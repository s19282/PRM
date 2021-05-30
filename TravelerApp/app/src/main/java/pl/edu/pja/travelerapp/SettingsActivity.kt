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
        binding.button2.setOnClickListener{
            val intent = Intent().apply {
                putExtra("textColor",binding.colorSpinner.selectedItem.toString())
                putExtra("textSize",binding.textSizeSpinner.selectedItem.toString())
                if(binding.radius.text.isNotEmpty())
                {
                    putExtra("radius",binding.radius.text.toString().toInt())
                }
            }
            println(binding.colorSpinner.selectedItem.toString())
            println(binding.textSizeSpinner.selectedItem.toString())
            println(binding.radius.text.toString().toInt())
            setResult(Activity.RESULT_OK,intent)
            finish()
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