package com.youama.nexus.parser.collector;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Belicza <87.bdavid@gmail.com>
 * @since 2015.07.11.
 */
public class CollectedItemTest extends TestCase {

    @Test
    public void testAddItem() {
        // For addItem
        CollectedItem item = new CollectedItem();
        item.addItem("something/thing.png");
        assertEquals("something/thing.png", item.data.get(0));

        item.addItem("something/thing2.png");
        assertEquals("something/thing2.png", item.data.get(1));

        // For addItem overLoad
        CollectedItem item2 = new CollectedItem();
        List<String> items = new ArrayList<String>();
        items.add("something/thing.png");
        items.add("something/thing2.png");
        item2.addItem(items);
        assertEquals(2, item2.data.size());
        assertEquals("something/thing2.png", item2.data.get(1));
    }

    @Test
    public void testGetItem() {
        CollectedItem item = new CollectedItem();
        item.addItem("something/thing.png");
        assertEquals(1, item.getData().size());
    }
}