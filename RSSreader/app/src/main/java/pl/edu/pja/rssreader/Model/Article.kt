package pl.edu.pja.rssreader.Model

import android.net.Uri
import java.util.regex.Matcher
import java.util.regex.Pattern

data class Article(
    val newsTitle: String?,
    var description: String?,
    val link: String?,
    val photo: Uri?
)
{
    constructor(item: Item) : this(item.title, item.description, item.link, Uri.parse(item.enclosure?.url))
    {
//        var p: Pattern = Pattern.compile("<.*>(.*)")
//        var m: Matcher = p.matcher(item.description.toString())
//        if(m.matches())
//            description = m.group(1)
//        else
    }
}