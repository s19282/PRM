package pl.edu.pja.rssreader.adapter

import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.rssreader.News
import pl.edu.pja.rssreader.databinding.ItemNewsBinding

class NewsItem(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(news: News) {
        binding.apply {
            newsTitle.text = news.newsTitle
            description.text = news.description
//            photo.setImageDrawable(news.photo)
        }
    }
}