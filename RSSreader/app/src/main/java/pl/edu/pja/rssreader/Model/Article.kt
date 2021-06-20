package pl.edu.pja.rssreader.Model

import android.graphics.drawable.Drawable

data class Article(
    val newsTitle: String?,
    val description: String?,
    val link: String?,
    val photo: Drawable?
)
{
    constructor(item: Item, string: String) : this(item.title, item.description, item.link, Drawable.createFromPath(string))
}