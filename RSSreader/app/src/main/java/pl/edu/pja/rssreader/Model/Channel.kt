package pl.edu.pja.rssreader.Model

import android.media.Image
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(strict = false, name = "channel")
data class Channel(
    @field:ElementList(inline=true) var item: List<Item>?,
//    @field:Element(name = "image", required = false) var image: Image?,
)
{
    constructor() : this(null)
}