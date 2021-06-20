import pl.edu.pja.rssreader.Model.Article

object Shared {
    val newsList = mutableListOf(
        Article("news1", "description1", "https://www.google.com/",null),
        Article("news2", "description2", "https://duckduckgo.com/",null),
        Article("news3", "description", "https://www.bing.com/",null),
    )
}