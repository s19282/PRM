package pl.edu.pja.rssreader.Model

import android.media.Image
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false,name = "item")
data class Item (
    @field:Element(name = "title", required = false) var title: String?,
    @field:Element(name = "link", required = false) var link: String?,
    @field:Element(name = "description", required = false) var description: String?,
    @field:Element(name = "enclosure", required = false) var enclosure: Enclosure?,
)
{
    constructor() : this("","","",null)
}