package pl.edu.pja.rssreader.Activity

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.edu.pja.rssreader.Model.Article
import pl.edu.pja.rssreader.Parser.ApiClient
import pl.edu.pja.rssreader.adapter.ArticleAdapter
import pl.edu.pja.rssreader.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val articleAdapter by lazy { ArticleAdapter(this) }
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button3.setOnClickListener{
            startActivity(Intent(this, FavActivity::class.java))
        }
        auth = FirebaseAuth.getInstance()
        findNews()
        updateList()
    }

    private fun updateList() {
        binding.recyclerView.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(context)
        }
        articleAdapter.articles = Shared.listOfArticles
    }
    private fun findNews() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = ApiClient.apiService.getItems()

                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    content?.channel?.item?.forEach{
                        Shared.listOfArticles.add(Article(it))
                    }
                    runOnUiThread {
                        articleAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error Occurred: ${response.message()}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Error Occurred: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun login(view: View) {
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            Toast.makeText(this, "You are currently logged", Toast.LENGTH_LONG).show()
        }
    }

    fun crashApp(view: View) {
        if (auth.currentUser == null) {
            auth.createUserWithEmailAndPassword("asdfasdf@gmail.com", "234567")
                .addOnSuccessListener {
                    Toast.makeText(this, "Zarejestrowano", Toast.LENGTH_LONG).show()
                }
            auth.signInWithEmailAndPassword("asdfasdf@gmail.com", "234567")
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Zalogowano ${it.user?.uid} ${it.user?.email}",
                        Toast.LENGTH_LONG
                    ).show()
                }
        } else {
            Toast.makeText(
                this,
                "Zalogowano ${auth.currentUser?.uid} ${auth.currentUser?.email}",
                Toast.LENGTH_LONG
            ).show()
            FirebaseDatabase.getInstance()
                .getReference("users")
                .child("${auth.currentUser?.uid}")
                .setValue("${auth.currentUser?.email}")
        }
    }

    fun logout(view: View) {
        if (auth.currentUser != null) {
            auth.signOut()
            Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "You're not logged!", Toast.LENGTH_LONG).show()
        }
    }
}