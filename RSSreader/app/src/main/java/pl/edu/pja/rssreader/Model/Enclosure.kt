package pl.edu.pja.rssreader.Model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "enclosure")
data class Enclosure constructor(
    @field:Element(name = "length") var length: String?,
    @field:Element(name = "type") var type: String?,
    @field:Element(name = "url") var url: String?
)
{
    constructor() : this("", "", "")
}