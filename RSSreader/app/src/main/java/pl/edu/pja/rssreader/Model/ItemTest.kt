package pl.edu.pja.rssreader.Model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementArray
import org.simpleframework.xml.Root

@Root(strict = false, name = "item")
data class ItemTest(
    @field:Element(name = "link", required = false) var link: String?,
    @field:Element(name = "guid", required = false) var guid: String?,
    @field:Element(name = "description", required = false) var description: String?,
    @field:Element(name = "title", required = false) var title: String?,
    @field:Element(name = "subtitle", required = false) var subtitle: String?,
)
{
    constructor() : this("","","","","")
}
