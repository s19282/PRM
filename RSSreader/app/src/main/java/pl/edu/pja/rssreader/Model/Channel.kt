package pl.edu.pja.rssreader.Model

data class Channel(
    val image: Image? = null,
    val item: Array<Item>? = null,
    val lastBuildDate: String? = null,
    val link: String? = null,
    val description: String? = null,
    val generator: String? = null,
    val language: String? = null,
    val title: String? = null,
)
