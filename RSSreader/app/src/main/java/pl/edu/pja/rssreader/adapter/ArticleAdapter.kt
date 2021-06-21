package pl.edu.pja.rssreader.adapter

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import pl.edu.pja.rssreader.Activity.MainActivity
import pl.edu.pja.rssreader.Model.Article
import pl.edu.pja.rssreader.databinding.ItemNewsBinding

class ArticleAdapter(private val mainActivity: MainActivity) : RecyclerView.Adapter<ArticleItem>() {
    var articles: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItem {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleItem(binding)
            .also { holder ->

            binding.root.setOnClickListener {
                it.setBackgroundColor(Color.parseColor("#94979c"))
                openNews(holder.layoutPosition)
            }
            binding.root.setOnLongClickListener{
                addToFavourites(holder.layoutPosition)
            }
        }
    }

    private fun openNews(index: Int)
    {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(
            mainActivity,
            Uri.parse(Shared.listOfArticles[index].link)
        )
    }
    private fun addToFavourites(index: Int) : Boolean
    {
        if(mainActivity.auth.currentUser==null)
        {
            Toast.makeText(mainActivity, "Log in first!", Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(mainActivity, "Added", Toast.LENGTH_LONG).show()
            FirebaseDatabase.getInstance()
                .getReference("favourites")
                .child("$index")
                .child("description")
                .setValue("${Shared.listOfArticles[index].description}")

            FirebaseDatabase.getInstance()
                .getReference("favourites")
                .child("$index")
                .child("link")
                .setValue("${Shared.listOfArticles[index].link}")

            FirebaseDatabase.getInstance()
                .getReference("favourites")
                .child("$index")
                .child("newsTitle")
                .setValue("${Shared.listOfArticles[index].newsTitle}")

            FirebaseDatabase.getInstance()
                .getReference("favourites")
                .child("$index")
                .child("photo")
                .setValue("${Shared.listOfArticles[index].photo}")

        }
        return true
    }

    override fun onBindViewHolder(holder: ArticleItem, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}