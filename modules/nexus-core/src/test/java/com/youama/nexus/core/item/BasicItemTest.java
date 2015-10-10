package com.youama.nexus.core.item;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Belicza
 * @since 0.1.0
 */
public class BasicItemTest {

    @Test
    public void testAddItem() {
        // For addItem
        BasicItem item = new BasicItem();
        item.addItem("something/thing.png");
        assertEquals("something/thing.png", item.data.get(0));

        item.addItem("something/thing2.png");
        assertEquals("something/thing2.png", item.data.get(1));

        // For addItem overload
        BasicItem item2 = new BasicItem();
        List<String> items = new ArrayList<String>();
        items.add("something/thing.png");
        items.add("something/thing2.png");
        item2.addItem(items);
        assertEquals(2, item2.data.size());
        assertEquals("something/thing2.png", item2.data.get(1));
    }

    @Test
    public void testHasData() {
        BasicItem item = new BasicItem();
        assertFalse(item.hasData());

        item.addItem("value");
        assertTrue(item.hasData());
    }

    @Test
    public void testGetData() {
        BasicItem item = new BasicItem();
        item.addItem("something/thing.png");
        assertEquals(1, item.getData().size());
    }
}