package pl.edu.pja.rssreader.Model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false, name = "enclosure")
data class Enclosure constructor(
    @field:Attribute(name = "url") var url: String?,
)
{
    constructor() : this( "")
}
