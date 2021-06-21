package pl.edu.pja.rssreader.Activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import pl.edu.pja.rssreader.Model.Article
import pl.edu.pja.rssreader.adapter.ArticleAdapter
import pl.edu.pja.rssreader.databinding.ActivityFavBinding
import pl.edu.pja.rssreader.databinding.ActivityMainBinding

class FavActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFavBinding.inflate(layoutInflater) }
    val listOfFavArticles = mutableListOf<Article>()
//    private val articleAdapter by lazy { ArticleAdapter(this) }

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getFromFirebase()
    }

    private fun getFromFirebase()
    {
        val tmp = FirebaseDatabase.getInstance().getReference("favourites").parent
        val tmp2 = FirebaseDatabase.getInstance().getReference("favourites").key
        val tmp3 = FirebaseDatabase.getInstance().getReference("favourites").child("2").key
        FirebaseDatabase.getInstance().getReference("favorites")
        val asdf = ""
    }
}