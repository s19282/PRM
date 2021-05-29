package pl.edu.pja.travelerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pl.edu.pja.travelerapp.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDescriptionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener{
            println("clicked")
            val intent = Intent()
            intent.putExtra("note",binding.description.text.toString())
            setResult(RESULT_OK,intent)
            finish()
        }

    }


}