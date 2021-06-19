package pl.edu.pja.rssreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.edu.pja.rssreader.databinding.ActivityFavBinding
import pl.edu.pja.rssreader.databinding.ActivityMainBinding

class FavActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFavBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}