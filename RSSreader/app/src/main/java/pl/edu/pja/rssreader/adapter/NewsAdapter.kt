package pl.edu.pja.rssreader.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.rssreader.News
import pl.edu.pja.rssreader.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsItem>() {
    var newses: List<News> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItem {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsItem(binding)
            .also { holder ->
            binding.root.setOnClickListener {
//                parent.context.startActivity(Intent(parent.context, AddActivity::class.java).putExtra("position", holder.layoutPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: NewsItem, position: Int) {
        holder.bind(newses[position])
    }

    override fun getItemCount(): Int = newses.size
}