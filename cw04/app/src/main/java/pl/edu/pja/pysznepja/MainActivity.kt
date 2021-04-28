package pl.edu.pja.pysznepja

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pl.edu.pja.pysznepja.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        binding.addButton.setOnClickListener { openAddActivity()}
    }

    fun openAddActivity(view: View)
    {
        startActivity(Intent(this, AddActivity::class.java))
    }
}