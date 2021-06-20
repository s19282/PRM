import pl.edu.pja.rssreader.News

object Shared {
    val newsList = mutableListOf(
        News("news1", "description1", "https://www.google.com/",null),
        News("news2", "description2", "https://duckduckgo.com/",null),
        News("news3", "description", "https://www.bing.com/",null),
    )
}