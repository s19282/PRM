package pl.edu.pja.rssreader.Model

import org.simpleframework.xml.ElementArray
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name = "channel")
data class Channel(
    @field:ElementList(inline = true, entry = "item") var item: List<Item>?,
//    val image: Image? = null,
//    val lastBuildDate: String? = null,
//    val link: String? = null,
//    val description: String? = null,
//    val generator: String? = null,
//    val language: String? = null,
//    val title: String? = null,
)
