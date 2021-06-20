package pl.edu.pja.rssreader.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "item", strict = false)
public class ItemJava {
    @Element(name = "title", required = false)
    private String title;
    @Element(name = "link", required = false)
    private String link;
    @Element(name = "comments", required = false)
    private String comments;
    @Element(name = "dc:creator", required = false)
    private String creator;
    @Element(name = "pubDate", required = false)
    private String pubDate;
    @ElementList(name = "category", inline = true, required = false)
    private ArrayList<String> category;
    @Element(name = "guid", required = false)
    private String guid;
    @Element(name = "description", required = false)
    private String description;
    @Element(name = "wfw:commentRss", required = false)
    private String commentRss;
    @Element(name = "slash:comments", required = false)
    private String sComments;

    @Override
    public String toString() {
        return "ItemJava{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public ItemJava() {
    }
}
