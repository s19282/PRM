package pl.edu.pja.rssreader.Model

import android.net.Uri

data class Article(
    val newsTitle: String?,
    val description: String?,
    val link: String?,
    val photo: Uri?
)
{
    constructor(item: Item) : this(item.title, item.description, item.link, Uri.parse(item.enclosure?.url))
}