package pl.edu.pja.rssreader.Model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementArray
import org.simpleframework.xml.Root

@Root(strict = false, name = "item")
class Item (
    @field:Element(name = "comments", required = false) var comments: String?,
    @field:Element(name = "link", required = false) var link: String?,
    @field:Element(name = "guid", required = false) var guid: String?,
    @field:Element(name = "description", required = false) var description: String?,
    @field:Element(name = "title", required = false) var title: String?,
    @field:ElementArray(name = "category", required = false) var category: Array<String>?,
    @field:Element(name = "pubDate", required = false) var pubDate: String?,
)
{
    constructor() : this("","","","","", arrayOf(),"")
}