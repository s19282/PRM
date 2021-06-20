package pl.edu.pja.rssreader.adapter

import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.rssreader.Model.Article
import pl.edu.pja.rssreader.databinding.ItemNewsBinding

class ArticleItem(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.apply {
            newsTitle.text = article.newsTitle
            description.text = article.description
//            photo.setImageDrawable(news.photo)
        }
    }
}