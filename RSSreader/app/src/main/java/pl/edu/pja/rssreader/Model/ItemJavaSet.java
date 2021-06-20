package pl.edu.pja.rssreader.Model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "channel")
public class ItemJavaSet
{
    @ElementList(name = "item", inline = true, required = false)
    private ArrayList<ItemJava> item;

    @Override
    public String toString() {
        return super.toString();
    }
}
