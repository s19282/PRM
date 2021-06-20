package pl.edu.pja.rssreader.Model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false,name = "rss")
data class Rss(
    @field:Element(name = "channel", required = false) var channel: Channel?,
)
{
    constructor() : this(null)
}