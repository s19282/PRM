package pl.edu.pja.rssreader.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pl.edu.pja.rssreader.Model.Article
import pl.edu.pja.rssreader.databinding.ItemNewsBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class ArticleItem(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.apply {
            newsTitle.text = article.newsTitle

            val p: Pattern = Pattern.compile("<.*>(.*)")
            val m: Matcher = p.matcher(article.description.toString())
            if(m.matches())
                description.text = m.group(1)
            else
                description.text = article.description

            Picasso.get().load(article.photo).into(img)
        }
    }
}