package pl.edu.pja.rssreader.Model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "image")
data class Image(
//    @field:Element(name = "width", required = false) var width: String? ,
    @field:Element(name = "title", required = false) var title: String?,
    @field:Element(name = "url", required = false) var url: String?,
    @field:Element(name = "link", required = false) var link: String?,
//    @field:Element(name = "height", required = false) var height: String?,
)
{
    constructor() : this("","","")
}
