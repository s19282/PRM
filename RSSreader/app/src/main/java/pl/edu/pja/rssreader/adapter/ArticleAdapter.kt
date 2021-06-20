package pl.edu.pja.rssreader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.rssreader.Model.Article
import pl.edu.pja.rssreader.databinding.ItemNewsBinding

class ArticleAdapter : RecyclerView.Adapter<ArticleItem>() {
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
//                parent.context.startActivity(Intent(parent.context, AddActivity::class.java).putExtra("position", holder.layoutPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: ArticleItem, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}