package pl.edu.pja.rssreader.Model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "enclosure")
data class Enclosure constructor(
    @field:Element(name = "length", required = false) var length: String?,
    @field:Element(name = "type", required = false) var type: String?,
    @field:Element(name = "url", required = false) var url: String?
)
{
    constructor() : this("", "", "")
}